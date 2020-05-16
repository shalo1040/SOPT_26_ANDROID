package com.example.signupserver.network

import com.example.signupserver.data.signup.RequestSignup
import com.example.signupserver.data.signup.ResponseSignup
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RequestSignupInterface {
    @POST("/user/signup")
    fun requestSignup(@Body body : RequestSignup) : Call<ResponseSignup>
}