package com.zatec.features.characters.data

import android.net.Uri
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.zatec.features.characters.persistence.CharacterData
import com.zatec.features.characters.ui.CharacterUi
import com.zatec.gotapp.core.data.DataMapper
import java.util.UUID


/**
 * Character response json parsed character info object
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
 * @constructor Create empty Character response
 */
@JsonClass(generateAdapter = true)
data class CharacterResponse(
    @Json(name = "aliases")
    val aliases: List<String>?,
    @Json(name = "allegiances")
    val allegiances: List<String>?,
    @Json(name = "books")
    val books: List<String>?,
    @Json(name = "born")
    val born: String?,
    @Json(name = "culture")
    val culture: String?,
    @Json(name = "died")
    val died: String?,
    @Json(name = "father")
    val father: String?,
    @Json(name = "gender")
    val gender: String?,
    @Json(name = "mother")
    val mother: String?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "playedBy")
    val playedBy: List<String>?,
    @Json(name = "povBooks")
    val povBooks: List<String>?,
    @Json(name = "spouse")
    val spouse: String?,
    @Json(name = "titles")
    val titles: List<String>?,
    @Json(name = "tvSeries")
    val tvSeries: List<String>?,
    @Json(name = "url")
    val url: String?
): DataMapper<CharacterResponse, CharacterData, CharacterUi> {
    override fun toData(): CharacterData {
        return CharacterData(
            aliases?: listOf(),
            allegiances?: listOf(),
            books?: listOf(),
            born?: "",
            culture?: "",
            died?: "",
            father?: "",
            gender?: "",
            mother?: "",
            name?: "",
            playedBy?: listOf(),
            povBooks?: listOf(),
            spouse?: "",
            titles?: listOf(),
            tvSeries?: listOf(),
            url?: "",
            id = Uri.parse(url).lastPathSegment?:UUID.randomUUID().toString()
        )
    }

    override fun toUi(): CharacterUi {
        return CharacterUi(
            aliases?: listOf(),
            allegiances?: listOf(),
            books?: listOf(),
            born?: "",
            culture?: "",
            died?: "",
            father?: "",
            gender?: "",
            mother?: "",
            name?: "",
            playedBy?: listOf(),
            povBooks?: listOf(),
            spouse?: "",
            titles?: listOf(),
            tvSeries?: listOf(),
            url?: "")
    }
}