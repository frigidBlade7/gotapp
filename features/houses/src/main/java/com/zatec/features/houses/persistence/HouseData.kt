package com.zatec.features.houses.persistence

import androidx.room.Entity
import androidx.room.PrimaryKey

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