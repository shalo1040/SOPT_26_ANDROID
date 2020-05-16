package com.example.signupserver.network

import com.example.signupserver.data.signin.RequestSignin
import com.example.signupserver.data.signin.ResponseSignin
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RequestSigninInterface {
    @POST("/user/signin")
    fun requestSignin(@Body body : RequestSignin) : Call<ResponseSignin>
}