package com.example.signupandsignin

import android.app.Activity
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

        btn_signIn.setOnClickListener {
            if(et_email.text.isNullOrBlank() || et_passwd.text.isNullOrBlank())
                Toast.makeText(this, "이메일과 비밀번호를 입력하세요", Toast.LENGTH_SHORT).show()
            else
                startActivity(Intent(this, MainActivity::class.java))
        }
    }

    fun signUp(view: View) {
        val intent = Intent(this, SignupActivity::class.java)
        startActivityForResult(intent, REQUEST_CODE);
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            et_email.setText(data?.getStringExtra("EMAIL"))
            et_passwd.setText(data?.getStringExtra("PASSWORD"))
        } else Toast.makeText(this, "회원가입에 실패했습니다.", Toast.LENGTH_SHORT).show()
    }
}