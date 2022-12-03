package com.zatec.features.characters.persistence

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.zatec.features.characters.ui.CharacterUi


/**
 * Character data to store Character info from api into room database
 *
 * @property aliases
 * @property allegiances
 * @property books
 * @property born
 * @property culture
 * @property died
 * @property father
 * @property gender
 * @property mother
 * @property name
 * @property playedBy
 * @property povBooks
 * @property spouse
 * @property titles
 * @property tvSeries
 * @property url
 * @property id
 * @constructor Create empty Character data
 */
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

/**
 * To ui
 *
 * @return
 */
fun CharacterData.toUi(): CharacterUi {
    return CharacterUi(
        aliases,
        allegiances,
        books,
        born,
        culture,
        died,
        father,
        gender,
        mother,
        name,
        playedBy,
        povBooks,
        spouse,
        titles,
        tvSeries,
        url
    )
}