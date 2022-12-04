package com.zatec.features.houses.persistence

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.zatec.features.houses.ui.HouseUi

/**
 * House data parsed from json [HouseResponse]
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
 * @property id
 * @constructor Create empty House data
 */
@Entity
class HouseData (
    val ancestralWeapons: List<String> = listOf(),
    val cadetBranches: List<String> = listOf(),
    val coatOfArms: String = "",
    val currentLord: String= "",
    val diedOut: String= "",
    val founded: String= "",
    val founder: String= "",
    val heir: String= "",
    val name: String= "",
    val overlord: String= "",
    val region: String= "",
    val seats: List<String> = listOf(),
    val swornMembers: List<String> = listOf(),
    val titles: List<String> = listOf(),
    val url: String ="",
    val words: String ="",
    @PrimaryKey
    val id: String = "")

//extension function
fun HouseData.toUi(): HouseUi {
    return HouseUi(
        ancestralWeapons,
        cadetBranches,
        coatOfArms,
        currentLord,
        diedOut,
        founded,
        founder,
        heir,
        name,
        overlord,
        region,
        seats,
        swornMembers,
        titles,
        url,
        words)
}