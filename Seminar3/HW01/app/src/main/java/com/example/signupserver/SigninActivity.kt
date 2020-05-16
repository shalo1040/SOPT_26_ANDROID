package com.example.signupserver

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.signupserver.data.signin.RequestSignin
import com.example.signupserver.network.RequestToServer
import com.example.signupserver.network.customEnqueue
import kotlinx.android.synthetic.main.activity_signin.*

class SigninActivity : AppCompatActivity() {
    val requestToServer = RequestToServer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        et_email.textChangedListener {
            if(it.isNullOrBlank())
                showToast("이메일이 입력되지 않았습니다.")
        }

        //로그인 시도
        btn_signIn.setOnClickListener {
            if(et_email.text.isNullOrBlank() || et_passwd.text.isNullOrBlank())
                showToast("아이디와 비밀번호를 확인해주세요.")
            else {
                requestToServer.signinService.requestSignin(
                    RequestSignin(
                        id = et_email.text.toString(),
                        password = et_passwd.text.toString()
                    )
                ).customEnqueue(
                    onError = {showToast("로그인 실패")},
                    onSuccess = {
                        if(it.success) {
                            showToast("로그인 성공!")
                            startActivity(Intent(this, MainActivity::class.java))
                            finish()
                        } else showToast("아이디와 비밀번호를 확인해주세요.")
                    }
                )
            }
            //customEnqueue를 안쓰면 코드가 길어짐 + SignupActivity에서도 중복되는 코드를 써줘야함
//            else {
//                requestToServer.signinService.requestSignin(
//                    RequestSignin(
//                        id = et_email.text.toString(),
//                        password = et_passwd.text.toString()
//                    )
//                ).enqueue(object : Callback<ResponseSignin>{
//                    override fun onFailure(call: Call<ResponseSignin>, t: Throwable) {
//                        showToast("통신 실패")
//                    }
//
//                    override fun onResponse(
//                        call: Call<ResponseSignin>,
//                        response: Response<ResponseSignin>
//                    ) {
//                        if(response.isSuccessful) {
//                            if(response.body()!!.success) {
//                                showToast("로그인 되었습니다!")
//                                startActivity(Intent(this@SigninActivity, MainActivity::class.java))
//                                finish()
//                            } else
//                                showToast("아이디와 비밀번호를 확인해주세요.")
//                        }
//                    }
//                })
//            }
        }
        //회원가입 창으로 이동
        txt_signUp.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
    }
}
