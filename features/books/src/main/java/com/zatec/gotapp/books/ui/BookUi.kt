package com.zatec.gotapp.books.ui

class BookUi (
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
)

fun BookUi.displayAuthors(): String{
    return authors.joinToString(separator = ", ")
}