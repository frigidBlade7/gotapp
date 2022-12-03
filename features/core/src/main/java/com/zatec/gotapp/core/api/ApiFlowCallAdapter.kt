package com.zatec.gotapp.core.api

import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

/**
 *  call adapter delegate for ApiResponse class
 *
 * @property resultType is the response from the server as a <Type>
 * @constructor Create empty Api response call adapter
 */
class ApiResponseCallAdapter (
    private val resultType: Type
) : CallAdapter<Type, Call<ApiResponse<Type>>> {

    override fun responseType(): Type = resultType

    override fun adapt(call: Call<Type>): Call<ApiResponse<Type>> {
        return ApiResponseCall(call)
    }
}