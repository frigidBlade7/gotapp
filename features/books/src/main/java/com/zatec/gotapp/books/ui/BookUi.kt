package com.zatec.gotapp.books.ui

import com.zatec.gotapp.books.ui.BookUi.Companion.displayFormat
import com.zatec.gotapp.books.ui.BookUi.Companion.serverFormat
import timber.log.Timber
import java.text.SimpleDateFormat

data class BookUi (
    val authors: List<String>,
    val characters: List<String>,
    val country: String = "",
    val isbn: String = "",
    val mediaType: String = "",
    val name: String = "",
    val numberOfPages: Int = 0,
    val povCharacters: List<String>,
    val publisher: String = "",
    val released: String = "",
    val url: String = ""
){
    companion object {
        val displayFormat = SimpleDateFormat("MMM dd, yyyy")
        val serverFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
    }
}

fun BookUi.displayAuthors(): String{
    return authors.joinToString(separator = ", ")
}

fun BookUi.formatDate(): String{

    return try {
        displayFormat.format(serverFormat.parse(this.released))
    }catch (e: Exception){
        Timber.e(e)
        this.released
    }
}