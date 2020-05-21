package com.example.review.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RequestToServer {
    var retrofit = Retrofit.Builder()
        .baseUrl("http://13.209.144.115:3333")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var signinService : RequestSigninInterface = retrofit.create(RequestSigninInterface::class.java)
    var signupService: RequestSignupInterface = retrofit.create(RequestSignupInterface::class.java)
}