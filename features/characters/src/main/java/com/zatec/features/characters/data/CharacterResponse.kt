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
    val aliases: List<String>? = null,
    @Json(name = "allegiances")
    val allegiances: List<String>? = null,
    @Json(name = "books")
    val books: List<String>? = null,
    @Json(name = "born")
    val born: String? = null,
    @Json(name = "culture")
    val culture: String? = null,
    @Json(name = "died")
    val died: String? = null,
    @Json(name = "father")
    val father: String? = null,
    @Json(name = "gender")
    val gender: String? = null,
    @Json(name = "mother")
    val mother: String? = null,
    @Json(name = "name")
    val name: String? = null,
    @Json(name = "playedBy")
    val playedBy: List<String>? = null,
    @Json(name = "povBooks")
    val povBooks: List<String>? = null,
    @Json(name = "spouse")
    val spouse: String? = null,
    @Json(name = "titles")
    val titles: List<String>? = null,
    @Json(name = "tvSeries")
    val tvSeries: List<String>? = null,
    @Json(name = "url")
    val url: String? = null
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