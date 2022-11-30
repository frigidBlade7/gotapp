package com.zatec.gotapp.core.api

import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ApiResponseCall<T>(private val call: Call<T>): Call<ApiResponse<T>> {

    //wrap in ApiResponseCall
    override fun clone(): Call<ApiResponse<T>> = ApiResponseCall(call.clone())

    //dont use execute directly

    override fun execute(): Response<ApiResponse<T>> =
        throw UnsupportedOperationException("Requests should not be async")

    //handle success and failure
    override fun enqueue(callback: Callback<ApiResponse<T>>) {
        call.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                val result = ApiResponse.create(response)
                callback.onResponse(this@ApiResponseCall, Response.success(result))
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                val result = ApiResponse.create<T>(t)
                callback.onResponse(this@ApiResponseCall, Response.success(result))
            }

        })
    }

    override fun isExecuted(): Boolean = call.isExecuted

    override fun cancel() = call.cancel()

    override fun isCanceled(): Boolean = call.isCanceled

    override fun request(): Request = call.request()

    override fun timeout(): Timeout = call.timeout()
}