package com.example.signupserver.network

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun<ResponseType> Call<ResponseType>.customEnqueue(
    onFail : () -> Unit = { Log.d("network", "통신 실패")},
    onSuccess : (ResponseType) -> Unit,
    onError : () -> Unit
) {
    this.enqueue(object:Callback<ResponseType>{
        override fun onFailure(call: Call<ResponseType>, t: Throwable) {
            onFail()
        }

        override fun onResponse(call: Call<ResponseType>, response: Response<ResponseType>) {
            response.body()?.let {
                onSuccess(it)
            } ?: onError()
        }
    })
}