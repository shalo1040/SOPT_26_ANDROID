package com.example.signupserver.data.signin

data class ResponseSignin (
    val status : Int,
    val success : Boolean,
    val message : String,
    val data : AnyData?
)

data class AnyData (
    val jwt : String
)