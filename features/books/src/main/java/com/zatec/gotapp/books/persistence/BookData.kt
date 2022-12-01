package com.zatec.gotapp.books.persistence

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class BookData (
    val authors: List<String> = listOf(),
    val characters: List<String> = listOf(),
    val country: String = "",
    val isbn: String = "",
    val mediaType: String = "",
    val name: String = "",
    val numberOfPages: Int = 0,
    val povCharacters: List<String> = listOf(),
    val publisher: String = "",
    val released: String = "",
    val url: String = "",
    @PrimaryKey
    val id: String = "",
)