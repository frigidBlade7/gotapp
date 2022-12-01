package com.zatec.gotapp.core.utils


import com.zatec.features.core.R
import com.zatec.gotapp.core.api.ApiResponse
import com.zatec.gotapp.core.ui.UiResult
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

object NetworkConstants {
    const val API_TIMEOUT = 60L
}

fun <T> flowResult(coroutine: suspend () -> ApiResponse<T>) = flow {

    emit(UiResult.loading())
    try {
        //Invoke api call
        val apiResponse =  coroutine.invoke()
        Timber.d(apiResponse.toString())
        when(apiResponse){
            is ApiResponse.Success -> {
                Timber.d(apiResponse.data.toString())
                emit(UiResult.success(data = apiResponse.data, lastPage = apiResponse.lastPage))
            }
            is ApiResponse.Error -> {
                Timber.e(apiResponse.message)
                emit(UiResult.error(errorCode = apiResponse.code, errorMessage = apiResponse.message))
            }
            //else -> emit(UiResult.error(message = "Something went wrong"))
        }

    }catch (exception: Exception){
        Timber.e(exception)
        when(exception){
            is UnknownHostException -> emit(UiResult.error(errorCode = R.string.network_error))
            is SocketTimeoutException -> emit(UiResult.error(errorCode = R.string.socket_error))
            is IOException -> emit(UiResult.error(errorCode = R.string.io_error))

            else -> emit(UiResult.error(errorMessage = exception.message))
        }
    }
}

