package com.zatec.gotapp.books.ui

import com.zatec.gotapp.books.ui.BookUi.Companion.displayFormat
import com.zatec.gotapp.books.ui.BookUi.Companion.serverFormat
import timber.log.Timber
import java.text.SimpleDateFormat

/**
 * Book ui  object to display book info in recycler view
 *
 * @property authors
 * @property characters
 * @property country
 * @property isbn
 * @property mediaType
 * @property name
 * @property numberOfPages
 * @property povCharacters
 * @property publisher
 * @property released
 * @property url
 * @constructor Create empty Book ui
 */
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

/**
 * Display authors
 *
 * @return
 */
fun BookUi.displayAuthors(): String{
    return authors.joinToString(separator = ", ")
}

/**
 * Format date
 * extension function
 * @return date formatted in the pattern of [serverFormat]
 */
fun BookUi.formatDate(): String{

    return try {
        displayFormat.format(serverFormat.parse(this.released))
    }catch (e: Exception){
        Timber.e(e)
        this.released
    }
}