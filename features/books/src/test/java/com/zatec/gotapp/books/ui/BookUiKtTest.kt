package com.zatec.gotapp.books.ui

import com.zatec.gotapp.books.data.BookResponse
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class BookUiKtTest {

    private lateinit var fullResponse: BookResponse
    private lateinit var partialResponse: BookResponse

    @Before
    fun setUp() {
        fullResponse = BookResponse(
            name = "Nate",
            country = "Ghana",
            isbn = "123456",
            mediaType = "Ebook",
            numberOfPages = 50,
            publisher = "Barnes & Noble",
            released = "2000-10-31T00:00:00",
            url="https://www.google.com",
            characters = listOf("Mum","Dad"),
            authors = listOf("Nathany Att", "John Doe"),
            povCharacters = listOf("neighbors", "friends")
        )

        partialResponse = BookResponse(
            authors = listOf("Me myself and I")
        )
    }

    @After
    fun tearDown() {
    }

    @Test
    fun givenBookResponseWhenParamsHaveDataThenValuesShowData(){

        val responseUi = fullResponse.toUi()
        assertEquals("Nate", responseUi.name)
        assertEquals("Ghana", responseUi.country)
        assertEquals("123456", responseUi.isbn)
        assertEquals("Ebook", responseUi.mediaType)
        assertEquals(50, responseUi.numberOfPages)
        assertEquals("Barnes & Noble", responseUi.publisher)
        assertEquals("2000-10-31T00:00:00", responseUi.released)
        assertEquals("https://www.google.com", responseUi.url)
        assertEquals(listOf<String>("Mum","Dad"), responseUi.characters)
        assertEquals(listOf("Nathany Att", "John Doe"), responseUi.authors)
        assertEquals(listOf<String>("neighbors", "friends"), responseUi.povCharacters)
    }

    @Test
    fun givenBookResponseWithMultipleAuthorsWhendisplayAuthorsThenshowcommaseparatedauthors() {
        val responseUi = fullResponse.toUi()
        assertEquals("Nathany Att, John Doe", responseUi.displayAuthors())
    }


    @Test
    fun givenBookResponseWithSingleAuthorWhendisplayAuthorsThenshowauthor() {
        val singleAuthorResponse = partialResponse.toUi()
        assertEquals("Me myself and I", singleAuthorResponse.displayAuthors())
    }


    @Test
    fun givenDateWhenNotNullThenformatDate() {
        val responseUi = fullResponse.toUi()
        try {
            assertEquals("Oct 31,2000", BookUi.displayFormat.format(BookUi.serverFormat.parse(responseUi.released)))
        }catch (e: Exception){
            assertEquals("2000-10-31T00:00:00", responseUi.released)
        }
    }

    @Test
    fun givenDateWhenNullThenformatDateIsEmptyString() {
        val responseUi = partialResponse.toUi()
        try {
            assertEquals("", BookUi.displayFormat.format(BookUi.serverFormat.parse(responseUi.released)))
        }catch (e: Exception){
            assertEquals("", responseUi.released)
        }
    }
}