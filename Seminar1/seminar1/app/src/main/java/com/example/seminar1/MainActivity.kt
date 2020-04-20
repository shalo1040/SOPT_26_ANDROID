package com.example.seminar1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.login_relative_layout.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_relative_layout)

        login_btn.setOnClickListener {
            if(et_email.text.isNullOrBlank() || et_passwd.text.isNullOrBlank()){}

        }
    }
}
