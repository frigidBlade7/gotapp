package com.zatec.features.characters.ui

/**
 * Character ui Character data item as ui object component
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
 * @constructor Create empty Character ui
 */
data class CharacterUi(
    val aliases: List<String>,
    val allegiances: List<String>,
    val books: List<String>,
    val born: String,
    val culture: String,
    val died: String,
    val father: String,
    val gender: String,
    val mother: String,
    val name: String,
    val playedBy: List<String>,
    val povBooks: List<String>,
    val spouse: String,
    val titles: List<String>,
    val tvSeries: List<String>,
    val url: String
){
    /**
     * Display name
     *
     * @return shows alias or "Unidentified" if name is empty
     */
    fun displayName():String {
        return name.ifEmpty { if(aliases.isEmpty()) "Unidentified" else aliases[0] }
    }
}

/**
 * Display titles
 *
 * @return list of titles as a comma separated string
 */
fun CharacterUi?.displayTitles(): String{
    return this?.titles?.joinToString(separator = ", ")?:""
}

/**
 * Display actors
 *
 * @return list of actors as a comma separated string prefixed with "Played by:"
 */
fun CharacterUi?.displayActors(): String{
    return if(this?.playedBy?.get(0)?.isEmpty() == true)"" else "Played by: ${this?.playedBy?.joinToString(separator = ", ")?:""}"
}