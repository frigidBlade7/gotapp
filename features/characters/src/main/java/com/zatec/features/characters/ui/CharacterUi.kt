package com.zatec.features.characters.ui

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
    fun displayName():String {
        return name.ifEmpty { if(aliases.isEmpty()) "Unidentified" else aliases[0] }
    }
}

fun CharacterUi.displayTitles(): String{
    return titles.joinToString(separator = ", ")
}

fun CharacterUi.displayActors(): String{
    return if(playedBy[0].isEmpty())"" else "Played by: ${playedBy.joinToString(separator = ", ")}"
}