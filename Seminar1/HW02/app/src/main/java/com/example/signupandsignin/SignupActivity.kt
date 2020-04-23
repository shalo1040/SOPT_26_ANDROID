package com.example.signupandsignin

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_signup.*

class SignupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        btn_signUp.setOnClickListener {
            if(et_signUp_email.text.isNullOrBlank() || et_signUp_passwd.text.isNullOrBlank() || et_signUp_passwd2.text.isNullOrBlank() || et_github.text.isNullOrBlank())
                Toast.makeText(this, "항목을 모두 채워주세요.", Toast.LENGTH_SHORT).show()
            else if(et_signUp_passwd.text.toString() != et_signUp_passwd2.text.toString())
                Toast.makeText(this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
            else {
                val intent = Intent()
                intent.putExtra("EMAIL", et_signUp_email.text.toString())
                intent.putExtra("PASSWORD", et_signUp_passwd.text.toString())
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }
    }
}
