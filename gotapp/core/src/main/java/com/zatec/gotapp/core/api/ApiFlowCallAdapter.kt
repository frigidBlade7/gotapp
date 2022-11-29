package com.zatec.gotapp.core.api

import com.zatec.gotapp.core.ui.UiResult
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

class ApiFlowCallAdapter (
    private val resultType: Type
) : CallAdapter<Type, Call<Flow<UiResult<Type>>>> {

    override fun responseType(): Type = resultType

    override fun adapt(call: Call<Type>): Call<Flow<UiResult<Type>>> {
        return ApiFlowCall(call)
    }
}