package com.zatec.gotapp.core.api


import com.zatec.features.core.R
import retrofit2.Response
import timber.log.Timber
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

sealed class ApiResponse<T> {
    data class Success<T>(val data: T? = null): ApiResponse<T>()
    data class Error<T>(val code: Int? = null, val message: String? = null): ApiResponse<T>()

    companion object {
        //method that returns a Network Error if theres an issue with I/O and a generic Error for everything else
        fun <T> create(error: Throwable): ApiResponse<T> {
            Timber.e(error)
            return when(error){
                is SocketTimeoutException -> Error(code = R.string.socket_error)
                is UnknownHostException -> Error(code = R.string.network_error)
                is IOException -> Error(code = R.string.io_error)
                else -> Error(message = error.message ?: "unknown message")
            }
        }

        // handle all response codes here
        //method that formats the typed response when the server sends it
        fun <T> create(response: Response<T>): ApiResponse<T> {
            Timber.d("message: ${response.message()}\n" +
                    "response code: ${response.code()}\n" +
                    "response message body: ${response.errorBody()}\n" +
                    "response body: ${response.body()}")

            return if (response.isSuccessful){
                val body = response.body()
                if (body == null) {
                    Error(message = "empty body")
                }
                else {
                    //if none of these conditions stop us, lets return a ApiResponse.Success
                    Success(body)
                }
            }
            else {
                //if the response code is not 20X
                val msg = response.message()
                val errorMsg = if (msg.isNullOrEmpty()) {
                    response.errorBody()?.string()
                } else {
                    msg
                }
                Error(message = errorMsg ?: "unknown message")
            }
        }
    }
}