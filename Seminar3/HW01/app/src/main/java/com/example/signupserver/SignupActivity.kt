package com.example.signupserver

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.signupserver.data.signup.RequestSignup
import com.example.signupserver.network.RequestToServer
import com.example.signupserver.network.customEnqueue
import kotlinx.android.synthetic.main.activity_signup.*

class SignupActivity : AppCompatActivity() {
    val requestToServer = RequestToServer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        //회원가입
        btn_signUp.setOnClickListener {
            if(et_signUp_name.text.isNullOrBlank() || et_signUp_passwd.text.isNullOrBlank() || et_signUp_passwd2.text.isNullOrBlank()
                || et_signUp_id.text.isNullOrBlank() || et_signUp_email.text.isNullOrBlank() || et_signUp_phone.text.isNullOrBlank())
                Toast.makeText(this, "모든 항목을 채워주세요.", Toast.LENGTH_SHORT).show()
            else if(et_signUp_passwd.text.toString() != et_signUp_passwd2.text.toString())
                Toast.makeText(this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
            else {
                requestToServer.signupService.requestSignup(
                    RequestSignup(
                        id = et_signUp_id.text.toString(),
                        password = et_signUp_passwd.text.toString(),
                        name = et_signUp_name.text.toString(),
                        email = et_signUp_email.text.toString(),
                        phone = et_signUp_phone.text.toString()
                    )
                ).customEnqueue(
                    onError = {showToast("회원가입 실패")},
                    onSuccess = {
                        if(it.success){
                            showToast("회원가입 성공!")
                            startActivity(Intent(this@SignupActivity, SigninActivity::class.java))
                            finish()
                        }
                    }
                )
            }
            //customEnqueue를 안쓰면 코드가 길어짐 + SigninActivity에서도 중복되는 코드를 써줘야함
//            else {
//                requestToServer.service.requestSignup(
//                    RequestSignup(
//                        id = et_signUp_id.text.toString(),
//                        password = et_signUp_passwd.text.toString(),
//                        name = et_signUp_name.text.toString(),
//                        email = et_signUp_email.text.toString(),
//                        phone = et_signUp_phone.text.toString()
//                    )
//                ).enqueue(object : Callback<ResponseSignup> {
//                    override fun onFailure(call: Call<ResponseSignup>, t: Throwable) {
//                        //통신 실패
//                        showToast("통신 실패")
//                    }
//
//                    override fun onResponse(
//                        call: Call<ResponseSignup>,
//                        response: Response<ResponseSignup>
//                    ) {
//                        //통신 성공
//                        if(response.isSuccessful) {
//                            if(response.body()!!.success) {
//                                showToast("회원가입 성공!")
//                                startActivity(Intent(this@SignupActivity, MainActivity::class.java))
//                                finish()
//                            } else {
//                                showToast("입력값을 한번 더 확인해주세요.")
//                            }
//                        }
//                    }
//                })
//            }
        }
    }
}
