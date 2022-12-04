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
    val ancestralWeapons: List<String> = listOf(),
    val cadetBranches: List<Any> = listOf(),
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
    val swornMembers: List<Any> = listOf(),
    val titles: List<String> = listOf(),
    val url: String= "",
    val words: String= ""
)

/**
 * Display seats
 *
 * @return
 */
fun HouseUi?.displaySeats(): String? = this?.seats?.joinToString(separator = ", ")

fun HouseUi?.displayWeapons(): String? = this?.ancestralWeapons?.joinToString(separator = ", ")

fun HouseUi?.displayLord(): String = this?.currentLord?.ifEmpty { "A house has no Lord" }?:""

fun HouseUi?.displayLocation(): String = this?.region?.ifEmpty { "No known location" }?:""

fun HouseUi?.seatCount(): Int = this?.seats?.count()?:0

