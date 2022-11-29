package com.zatec.gotapp.core.utils

import com.zatec.gotapp.R
import com.zatec.gotapp.core.ui.UiResult
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import timber.log.Timber
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

object NetworkConstants {
    const val API_TIMEOUT = 60L
}

fun <T> flowError(throwable: Throwable) = flow<UiResult<T>> {
    emit(UiResult.error(error = throwable.message))
}

fun <T> flowResult(coroutine: suspend () -> Response<T>) = flow <UiResult<T>>{
    emit(UiResult.loading())
    try {
        //Invoke api call
        val apiResponse =  coroutine.invoke()
        emit(UiResult.success(data = (apiResponse as T)))
    }catch (exception: Exception){
        Timber.d(exception)
        when(exception){
            is IOException -> emit(UiResult.error(errorCode = R.string.io_error))
            is SocketTimeoutException -> emit(UiResult.error(errorCode = R.string.network_error))
            is UnknownHostException -> emit(UiResult.error(errorCode = R.string.socket_error))

            else -> emit(UiResult.error(error = exception.message))
        }
    }
}

