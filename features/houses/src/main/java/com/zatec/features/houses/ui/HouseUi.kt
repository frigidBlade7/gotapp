package com.zatec.features.houses.ui

/**
 * House ui
 * ui component of [HouseData] object
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
 * @constructor Create empty House ui
 */
data class HouseUi (
    val ancestralWeapons: List<String>,
    val cadetBranches: List<Any>,
    val coatOfArms: String,
    val currentLord: String,
    val diedOut: String,
    val founded: String,
    val founder: String,
    val heir: String,
    val name: String,
    val overlord: String,
    val region: String,
    val seats: List<String>,
    val swornMembers: List<Any>,
    val titles: List<String>,
    val url: String,
    val words: String
)

/**
 * Display seats
 *
 * @return
 */
fun HouseUi.displaySeats(): String{
    return seats.joinToString(separator = ", ")
}
