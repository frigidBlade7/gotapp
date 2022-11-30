package com.zatec.gotapp.core.ui

data class UiResult<T>(
    val isLoading: Boolean = false,
    val errorCode: Int? = null,
    val errorMessage: String? = null,
    val message: String? = null,
    val hasMore: Boolean = false,
    val data: T? = null
){
    companion object {
        fun <T> loading() = UiResult<T>(isLoading = true)
        fun <T> success(data: T, message: String? = null) = UiResult(isLoading = false, data = data, message = message)
        fun <T> error(errorCode: Int? = null, errorMessage: String? = null) = UiResult<T>(isLoading = false, errorCode= errorCode, errorMessage= errorMessage)
    }
}
