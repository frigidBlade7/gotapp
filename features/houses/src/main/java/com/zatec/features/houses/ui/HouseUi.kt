package com.zatec.features.houses.ui

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

fun HouseUi.displaySeats(): String{
    return seats.joinToString(separator = ", ")
}
