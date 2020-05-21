package com.example.review.network

import com.example.review.data.signup.RequestSignup
import com.example.review.data.signup.ResponseSignup
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RequestSignupInterface {
    @POST("/user/signup")
    fun requestSignup(@Body body: RequestSignup) : Call<ResponseSignup>
}