package com.zatec.gotapp.books.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.zatec.gotapp.books.persistence.BookData
import com.zatec.gotapp.books.ui.BookUi
import com.zatec.gotapp.core.data.DataMapper

/**
 * Book response
 * Json object mapping for a book from api
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
 * @constructor Create empty Book response
 */
@JsonClass(generateAdapter = true)
data class BookResponse(
    @Json(name = "authors")
    val authors: List<String>? = null,
    @Json(name = "characters")
    val characters: List<String>? = null,
    @Json(name = "country")
    val country: String? = null,
    @Json(name = "isbn")
    val isbn: String? = null,
    @Json(name = "mediaType")
    val mediaType: String? = null,
    @Json(name = "name")
    val name: String? = null,
    @Json(name = "numberOfPages")
    val numberOfPages: Int? = null,
    @Json(name = "povCharacters")
    val povCharacters: List<String>? = null,
    @Json(name = "publisher")
    val publisher: String? = null,
    @Json(name = "released")
    val released: String? = null,
    @Json(name = "url")
    val url: String? = null
): DataMapper<BookResponse, BookData, BookUi> {
    override fun toData(): BookData {
        return BookData()
    }

    override fun toUi(): BookUi {
        return BookUi(
            authors?: listOf(),
            characters?: listOf(),
            country?:"",
            isbn?:"",
            mediaType?:"",
            name?:"",
            numberOfPages?:0,
            povCharacters?: listOf(),
            publisher?:"",
            released?:"",
            url?:""
        )
    }
}