package com.zatec.features.houses.api

import android.net.Uri
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.zatec.features.houses.persistence.HouseData
import com.zatec.features.houses.ui.HouseUi
import com.zatec.gotapp.core.data.DataMapper
import java.util.*

/**
 * House response house response object from api parsed
 *
 * @property ancestralWeapons
 * @property cadetBranches
 * @property coatOfArms
 * @property currentLord
 * @property diedOut
 * @property founded
 * @property founder
 * @property heir
 * @property name
 * @property overlord
 * @property region
 * @property seats
 * @property swornMembers
 * @property titles
 * @property url
 * @property words
 * @constructor Create empty House response
 */
@JsonClass(generateAdapter = true)
data class HouseResponse(
    @Json(name = "ancestralWeapons")
    val ancestralWeapons: List<String>? = null,
    @Json(name = "cadetBranches")
    val cadetBranches: List<String>? = null,
    @Json(name = "coatOfArms")
    val coatOfArms: String? = null,
    @Json(name = "currentLord")
    val currentLord: String? = null,
    @Json(name = "diedOut")
    val diedOut: String? = null,
    @Json(name = "founded")
    val founded: String? = null,
    @Json(name = "founder")
    val founder: String? = null,
    @Json(name = "heir")
    val heir: String? = null,
    @Json(name = "name")
    val name: String? = null,
    @Json(name = "overlord")
    val overlord: String? = null,
    @Json(name = "region")
    val region: String? = null,
    @Json(name = "seats")
    val seats: List<String>? = null,
    @Json(name = "swornMembers")
    val swornMembers: List<String>? = null,
    @Json(name = "titles")
    val titles: List<String>? = null,
    @Json(name = "url")
    val url: String? = null,
    @Json(name = "words")
    val words: String? = null
): DataMapper<HouseResponse, HouseData, HouseUi> {
    override fun toData(): HouseData {
        return HouseData(
            ancestralWeapons?: listOf(),
            cadetBranches?: listOf(),
            coatOfArms?:"",
            currentLord?:"",
            diedOut?:"",
            founded?:"",
            founder?:"",
            heir?: "",
            name?:"",
            overlord?:"",
            region?:"",
            seats?: listOf(),
            swornMembers?: listOf(),
            titles = titles?: listOf(),
            url = url?:"",
            words = words?:"",
            id = Uri.parse(url).lastPathSegment?: UUID.randomUUID().toString()
        )
    }

    override fun toUi(): HouseUi {
        return HouseUi(
            ancestralWeapons?: listOf(),
            cadetBranches?: listOf(),
            coatOfArms?:"",
            currentLord?:"",
            diedOut?:"",
            founded?:"",
            founder?:"",
            heir?: "",
            name?:"",
            overlord?:"",
            region?:"",
            seats?: listOf(),
            swornMembers?: listOf(),
            titles = titles?: listOf(),
            url = url?:"",
            words = words?:""
        )
    }
}