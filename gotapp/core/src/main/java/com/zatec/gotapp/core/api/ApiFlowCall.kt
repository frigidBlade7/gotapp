package com.zatec.gotapp.core.api

import com.zatec.gotapp.core.ui.UiResult
import com.zatec.gotapp.core.utils.flowError
import com.zatec.gotapp.core.utils.flowResult
import kotlinx.coroutines.flow.Flow
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ApiFlowCall<T>(private val call: Call<T>): Call<Flow<UiResult<T>>> {

    //wrap in ApiFlowCall
    override fun clone(): Call<Flow<UiResult<T>>> = ApiFlowCall(call.clone())

    //dont use execute directly

    override fun execute(): Response<Flow<UiResult<T>>> =
        throw UnsupportedOperationException("Requests should not be async")

    //handle success and failure
    override fun enqueue(callback: Callback<Flow<UiResult<T>>>) {
        call.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                val result = flowResult { response }
                callback.onResponse(this@ApiFlowCall, Response.success(result))
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                val result = flowError<T>(t)
                callback.onResponse(this@ApiFlowCall, Response.success(result))

            }

        })
    }

    override fun isExecuted(): Boolean = call.isExecuted

    override fun cancel() = call.cancel()

    override fun isCanceled(): Boolean = call.isCanceled

    override fun request(): Request = call.request()

    override fun timeout(): Timeout = call.timeout()
}