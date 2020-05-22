package com.example.review

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.review.data.signup.RequestSignup
import com.example.review.network.RequestToServer
import com.example.review.network.customEnqueue
import kotlinx.android.synthetic.main.activity_signup.*

class SignupActivity : AppCompatActivity() {
    val requestToServer = RequestToServer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        btn_signUp.setOnClickListener {
            if(et_signUp_name.text.isNullOrBlank() || et_signUp_passwd.text.isNullOrBlank() || et_signUp_passwd2.text.isNullOrBlank()
                || et_signUp_id.text.isNullOrBlank() || et_signUp_email.text.isNullOrBlank() || et_signUp_phone.text.isNullOrBlank())
                showToast("모든 항목을 채워주세요")
            else if(et_signUp_passwd.text.toString() != et_signUp_passwd2.text.toString())
                showToast("비밀번호가 일치하지 않습니다")
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
                    onFail = { showToast("서버와 통신 실패!") },
                    onError = { showToast("입력값을 다시 한 번 확인해주세요!") },
                    onSuccess = {
                        if(it.success) {
                            val intent = Intent()
                            intent.putExtra("EMAIL", et_signUp_email.text.toString())
                            intent.putExtra("PASSWORD", et_signUp_passwd.text.toString())
                            setResult(Activity.RESULT_OK, intent)
                            finish()
                        }
                    }
                )
            }
        }
    }
}
