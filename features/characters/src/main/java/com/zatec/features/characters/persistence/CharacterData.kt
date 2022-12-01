package com.zatec.features.characters.persistence

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class CharacterData (
    val aliases: List<String> = listOf(),
    val allegiances: List<String> = listOf(),
    val books: List<String> = listOf(),
    val born: String = "",
    val culture: String = "",
    val died: String = "",
    val father: String = "",
    val gender: String = "",
    val mother: String = "",
    val name: String = "",
    val playedBy: List<String> = listOf(),
    val povBooks: List<String> = listOf(),
    val spouse: String = "",
    val titles: List<String> = listOf(),
    val tvSeries: List<String> = listOf(),
    val url: String = "",
    @PrimaryKey
    val id: String = "",
)