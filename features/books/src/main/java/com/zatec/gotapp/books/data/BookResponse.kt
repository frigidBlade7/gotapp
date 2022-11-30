package com.zatec.gotapp.books.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BookResponse(
    @Json(name = "authors")
    val authors: List<String>?,
    @Json(name = "characters")
    val characters: List<String>?,
    @Json(name = "country")
    val country: String?,
    @Json(name = "isbn")
    val isbn: String?,
    @Json(name = "mediaType")
    val mediaType: String?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "numberOfPages")
    val numberOfPages: Int?,
    @Json(name = "povCharacters")
    val povCharacters: List<String>?,
    @Json(name = "publisher")
    val publisher: String?,
    @Json(name = "released")
    val released: String?,
    @Json(name = "url")
    val url: String?
)