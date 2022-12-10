package com.zatec.gotapp.books.data

import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

class BookResponseTest {

    private lateinit var response: BookResponse

    @Test
    fun givenBookResponseWhenParamsAreNullThenValuesAreNull(){

        assertEquals(null, response.name)
        assertEquals(null, response.country)
        assertEquals(null, response.isbn)
        assertEquals(null, response.mediaType)
        assertEquals(null, response.numberOfPages)
        assertEquals(null, response.publisher)
        assertEquals(null, response.released)
        assertEquals(null, response.url)
        assertEquals(null, response.authors)
        assertEquals(null, response.characters)
        assertEquals(null, response.povCharacters)

        val responseUi = response.toUi()
    }

    @Test
    fun givenBookResponseUiWhenParamsAreNullThenDefaultValuesAreUsed() {
        val responseUi = response.toUi()
        assertEquals("", responseUi.name)
        assertEquals("", responseUi.country)
        assertEquals("", responseUi.isbn)
        assertEquals("", responseUi.mediaType)
        assertEquals(0, responseUi.numberOfPages)
        assertEquals("", responseUi.publisher)
        assertEquals("", responseUi.released)
        assertEquals("", responseUi.url)
        assertEquals(listOf<String>(), responseUi.authors)
        assertEquals(listOf<String>(), responseUi.characters)
        assertEquals(listOf<String>(), responseUi.povCharacters)
    }

    @Before
    fun setUp() {
        response = BookResponse()
    }

    @After
    fun tearDown() {
    }
}