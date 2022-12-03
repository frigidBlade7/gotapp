package com.zatec.gotapp.core.ui

/**
 * Ui result
 * ui class to hold data from api or room db
 * @param T data type that it contains
 * @property isLoading state of the data, can be true (api call made) or false (success or error)
 * @property errorCode error code for errors that don't have a message from the server, like IO/network/http exceptions
 * @property errorMessage error message for errors that have a response on the server
 * @property message success message for successful api requests
 * @property lastPage  if endpoint does not return a paged list, will be null. If it does, returns the last page of data to be loaded
 * @property data the actual data object parsed from json
 * @constructor Create empty Ui result
 */
data class UiResult<T>(
    val isLoading: Boolean = false,
    val errorCode: Int? = null,
    val errorMessage: String? = null,
    val message: String? = null,
    val lastPage: Int? = null,
    val data: T? = null
){
    companion object {
        fun <T> loading() = UiResult<T>(isLoading = true)
        fun <T> success(data: T, message: String? = null, lastPage: Int? = null) = UiResult(isLoading = false, data = data, message = message, lastPage = lastPage)
        fun <T> error(errorCode: Int? = null, errorMessage: String? = null) = UiResult<T>(isLoading = false, errorCode= errorCode, errorMessage= errorMessage)
    }
}
