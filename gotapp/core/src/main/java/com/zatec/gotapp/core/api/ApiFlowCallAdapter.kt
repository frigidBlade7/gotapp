package com.zatec.gotapp.core.api

import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

class ApiResponseCallAdapter (
    private val resultType: Type
) : CallAdapter<Type, Call<ApiResponse<Type>>> {

    override fun responseType(): Type = resultType

    override fun adapt(call: Call<Type>): Call<ApiResponse<Type>> {
        return ApiResponseCall(call)
    }
}