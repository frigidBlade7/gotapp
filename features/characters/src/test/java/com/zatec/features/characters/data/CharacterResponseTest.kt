package com.zatec.features.characters.data

import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

class CharacterResponseTest {

    lateinit var response: CharacterResponse

    @Before
    fun setUp() {
        response = CharacterResponse()
    }

    @After
    fun tearDown() {
    }

    @Test
    fun givenCharacterResponseWhenParamsAreNullThenValuesAreNull(){

        assertEquals(null, response.aliases)
        assertEquals(null, response.allegiances)
        assertEquals(null, response.books)
        assertEquals(null, response.born)
        assertEquals(null, response.culture)
        assertEquals(null, response.died)
        assertEquals(null, response.father)
        assertEquals(null, response.mother)
        assertEquals(null, response.name)
        assertEquals(null, response.playedBy)
        assertEquals(null, response.povBooks)
        assertEquals(null, response.spouse)
        assertEquals(null, response.titles)
        assertEquals(null, response.tvSeries)
        assertEquals(null, response.url)

        val responseUi = response.toUi()
    }

    @Test
    fun givenCharacterResponseUiWhenParamsAreNullThenDefaultValuesAreUsed() {
        val responseUi = response.toUi()

        assertEquals(listOf<String>(), responseUi.aliases)
        assertEquals(listOf<String>(), responseUi.allegiances)
        assertEquals(listOf<String>(), responseUi.books)
        assertEquals("", responseUi.born)
        assertEquals("", responseUi.culture)
        assertEquals("", responseUi.died)
        assertEquals("", responseUi.father)
        assertEquals("", responseUi.mother)
        assertEquals("", responseUi.name)
        assertEquals(listOf<String>(), responseUi.playedBy)
        assertEquals(listOf<String>(), responseUi.povBooks)
        assertEquals("", responseUi.spouse)
        assertEquals(listOf<String>(), responseUi.titles)
        assertEquals(listOf<String>(), responseUi.tvSeries)
        assertEquals("", responseUi.url)
    }
}