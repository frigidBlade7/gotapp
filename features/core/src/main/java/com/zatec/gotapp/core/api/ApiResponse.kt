package com.zatec.gotapp.core.api


import android.net.Uri
import com.zatec.features.core.R
import retrofit2.Response
import timber.log.Timber
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Api response class to wrap and handle all response types
 *
 * @param T data type of response
 * @constructor Create empty Api response
 */
sealed class ApiResponse<T> {
    /**
     * Success
     *
     * @param T data type of response
     * @property data response data
     * @property lastPage for paged responses, the last page number per the page size
     * @constructor Create empty Success
     */
    data class Success<T>(val data: T? = null, val lastPage: Int? = null): ApiResponse<T>()

    /**
     * Error
     *
     * @param T data type of response, unused atm
     * @property code error code if any, or null if an error message exists
     * @property message error message if any, or null if an error code exists
     * @constructor Create empty Error
     */
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
                    Error(message = "empty body") //return an error if the body is null
                }
                else {
                    var lastPage: Int? = null
                    val linkData = response.headers().find { it.first == "link" }?.second
                    linkData?.let {
                        val link = it.split(",").find { it.contains("rel=\"last\"") }?.split(";")?.first()?.replace(
                        ">","")?.replace("<","")
                        lastPage = Uri.parse(link).getQueryParameter("page")?.toInt()?:0
                    }

                    Success(body, lastPage)
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
                Timber.e(errorMsg)
                Error(message = errorMsg ?: "unknown message")
            }
        }
    }
}