package com.example.signupandsignin

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        //사용자가 로그인 했음을 저장
        val pref = getSharedPreferences("LOGIN", Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putBoolean("isLoginUser", true)
        editor.apply()

        //사용자 아이디와 비밀번호 토스트 메시지로 출력
        val sharedPref = getSharedPreferences("USER_INFO", Context.MODE_PRIVATE)
        val email = sharedPref.getString("EMAIL", "null")
        val password = sharedPref.getString("PASSWORD", "null")
        Toast.makeText(this, "아이디: ${email}\n비밀번호: ${password}", Toast.LENGTH_SHORT).show()
    }
}
