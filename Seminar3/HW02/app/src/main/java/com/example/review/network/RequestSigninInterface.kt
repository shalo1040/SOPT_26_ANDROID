package com.example.review.network

import com.example.review.data.signin.RequestSignin
import com.example.review.data.signin.ResponseSignin
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RequestSigninInterface {
    @POST("/user/signin")
    fun requestSignin(@Body body : RequestSignin) : Call<ResponseSignin>
}