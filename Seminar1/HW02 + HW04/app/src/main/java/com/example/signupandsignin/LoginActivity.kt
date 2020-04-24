package com.example.signupandsignin

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    val REQUEST_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()

        //로그인 유무 판단
        val pref = getSharedPreferences("LOGIN", Context.MODE_PRIVATE)
        val isLoginUser = pref.getBoolean("isLoginUser", false)
        if(isLoginUser) startActivity(Intent(this, MainActivity::class.java))

        //로그인 버튼 클릭 리스너
        btn_signIn.setOnClickListener {
            if(et_email.text.isNullOrBlank() || et_passwd.text.isNullOrBlank())
                Toast.makeText(this, "이메일과 비밀번호를 입력하세요", Toast.LENGTH_SHORT).show()
            else
                startActivity(Intent(this, MainActivity::class.java))
        }
    }

    //회원가입 선택하면 실행
    fun signUp(view: View) {
        val intent = Intent(this, SignupActivity::class.java)
        startActivityForResult(intent, REQUEST_CODE);
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            //로그인 입력창에 회원가입 정보 출력
            et_email.setText(data?.getStringExtra("EMAIL"))
            et_passwd.setText(data?.getStringExtra("PASSWORD"))

            //사용자가 기입한 정보 저장
            val sharedPref = getSharedPreferences("USER_INFO", Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putString("EMAIL", data?.getStringExtra("EMAIL"))
            editor.putString("PASSWORD", data?.getStringExtra("PASSWORD"))
            editor.apply()

            //메인 페이지로 이동
            startActivity(Intent(this, MainActivity::class.java))
        } else Toast.makeText(this, "회원가입에 실패했습니다.", Toast.LENGTH_SHORT).show()
    }
}