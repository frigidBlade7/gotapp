package com.zatec.features.characters.ui

import com.zatec.features.characters.data.CharacterResponse
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

class CharacterUiTest {
    lateinit var characterResponse: CharacterResponse
    lateinit var partialCharacterResponse: CharacterResponse

    
    @Before
    fun setUp() {
        characterResponse = CharacterResponse(
            name = "Nate",
            titles = listOf("android dev","the choosen one"),
            aliases = listOf("android dev","the choosen one"),
            books = listOf("https://anapioficeandfire.com/api/books/5"),
            born = "299 AC, at Pyke",
            culture = "TDD",
            father = "Attipoe X",
            mother = "Attipoe Y",
            allegiances = listOf("https://anapioficeandfire.com/api/houses/195"),
            url = "https://anapioficeandfire.com/api/characters/1"
        )
        partialCharacterResponse = CharacterResponse()
    }

    @After
    fun tearDown() {
    }

    @Test
    fun givenCharacterResponseAsUiWhenParamsAreNotNullThenUiValuesAreUsed(){
        val characterUi = characterResponse.toUi()
        assertEquals(listOf("android dev","the choosen one"), characterUi.aliases)
        assertEquals(listOf("android dev","the choosen one"), characterUi.titles)
        assertEquals(listOf("https://anapioficeandfire.com/api/books/5"), characterUi.books)
        assertEquals("299 AC, at Pyke", characterUi.born)
        assertEquals("TDD", characterUi.culture)
        assertEquals(listOf("https://anapioficeandfire.com/api/houses/195"), characterUi.allegiances)
        assertEquals("https://anapioficeandfire.com/api/characters/1", characterUi.url)
    }

    @Test
    fun displayName() {
        assertEquals("Nate", characterResponse.toUi().displayName())
        assertEquals("Unidentified", partialCharacterResponse.toUi().displayName())
    }

    @Test
    fun displayTitle() {
        assertEquals("android dev, the choosen one", characterResponse.toUi().displayTitles())
        assertEquals("", partialCharacterResponse.toUi().displayTitles())

    }
}