package com.zatec.features.houses.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.zatec.features.houses.persistence.HouseData
import com.zatec.features.houses.ui.HouseUi
import com.zatec.gotapp.core.data.DataMapper

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
    val ancestralWeapons: List<String>?,
    @Json(name = "cadetBranches")
    val cadetBranches: List<Any>?,
    @Json(name = "coatOfArms")
    val coatOfArms: String?,
    @Json(name = "currentLord")
    val currentLord: String?,
    @Json(name = "diedOut")
    val diedOut: String?,
    @Json(name = "founded")
    val founded: String?,
    @Json(name = "founder")
    val founder: String?,
    @Json(name = "heir")
    val heir: String?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "overlord")
    val overlord: String?,
    @Json(name = "region")
    val region: String?,
    @Json(name = "seats")
    val seats: List<String>?,
    @Json(name = "swornMembers")
    val swornMembers: List<Any>?,
    @Json(name = "titles")
    val titles: List<String>?,
    @Json(name = "url")
    val url: String?,
    @Json(name = "words")
    val words: String?
): DataMapper<HouseResponse, HouseData, HouseUi> {
    override fun toData(): HouseData {
        return HouseData()
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