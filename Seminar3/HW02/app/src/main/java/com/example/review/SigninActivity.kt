package com.example.review

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.review.data.signin.RequestSignin
import com.example.review.network.RequestToServer
import com.example.review.network.customEnqueue
import kotlinx.android.synthetic.main.activity_signin.*

class SigninActivity : AppCompatActivity() {
    val REQUEST_CODE = 1
    val requestToServer = RequestToServer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        btn_signIn.setOnClickListener {
            if(et_email.text.isNullOrBlank() || et_passwd.text.isNullOrBlank())
                showToast("아이디와 비밀번호를 입력해주세요")
            else {
                requestToServer.signinService.requestSignin(
                    RequestSignin(
                        id = et_email.text.toString(),
                        password = et_passwd.text.toString()
                    )
                ).customEnqueue(
                    onFail = { showToast("아이디와 비밀번호를 확인해주세요")},
                    onError = { showToast("아이디와 비밀번호를 확인해주세요")},
                    onSuccess = {
                        if(it.success) {
                            //사용자가 기입한 정보 저장
                            val sharedPref = getSharedPreferences("USER_INFO", Context.MODE_PRIVATE)
                            val editor = sharedPref.edit()
                            editor.putString("EMAIL", et_email.text.toString())
                            editor.putString("PASSWORD", et_passwd.text.toString())
                            editor.apply()

                            val pref = getSharedPreferences("LOGIN", Context.MODE_PRIVATE)
                            val edit = pref.edit()
                            edit.putBoolean("isLogin", true)
                            edit.apply()

                            showToast("환영합니다!")
                            startActivity(Intent(this, MainActivity::class.java))
                            finish()
                        } else showToast("아이디와 비밀번호를 확인해주세요")
                    }
                )
            }
        }
    }

    fun signUp(v: View) {
        startActivityForResult(Intent(this, SignupActivity::class.java), REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            et_email.setText(data?.getStringExtra("EMAIL"))
            et_passwd.setText(data?.getStringExtra("PASSWORD"))

            //사용자가 기입한 정보 저장
            val sharedPref = getSharedPreferences("USER_INFO", Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putString("EMAIL", data?.getStringExtra("EMAIL"))
            editor.putString("PASSWORD", data?.getStringExtra("PASSWORD"))
            editor.apply()

            showToast("회원가입 성공!")
            startActivity(Intent(this, MainActivity::class.java))
        } else showToast("회원가입에 실패했습니다.")
    }
}
