package com.zatec.gotapp.books.viewmodels

import androidx.paging.PagingData
import com.zatec.gotapp.books.FakeBookApi
import com.zatec.gotapp.books.data.BookResponse
import com.zatec.gotapp.books.repos.BooksRepository
import com.zatec.gotapp.books.ui.BooksListAdapter
import com.zatec.gotapp.books.usecase.PagedBooksUseCase
import com.zatec.gotapp.books.usecase.QueryBooksUseCase
import com.zatec.gotapp.core.api.ApiResponse
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class BooksViewModelTest {
    lateinit var booksViewModel: BooksViewModel
    val book1 = BookResponse(
        url="https://anapioficeandfire.com/api/books/1",
        name="A Game of Thrones",
        isbn="978-0553103540",
        authors= listOf("George R. R. Martin"),
        numberOfPages=694,
        publisher="Bantam Books",
        country="United States",
        mediaType="Hardcover",
        released="1996-08-01T00:00:00",
        characters = listOf("https://anapioficeandfire.com/api/characters/2"),
        povCharacters = listOf("https://anapioficeandfire.com/api/characters/148")
    )
    val book2 = BookResponse(
        url="https://anapioficeandfire.com/api/books/2",
        name="A Clash of Kings",
        isbn="978-0553108033",
        authors= listOf("George R. R. Martin"),
        numberOfPages=768,
        publisher="Bantam Books",
        country="United States",
        mediaType="Hardcover",
        released="1999-02-02T00:00:00",
        characters = listOf("https://anapioficeandfire.com/api/characters/2","https://anapioficeandfire.com/api/characters/12"),
        povCharacters = listOf("https://anapioficeandfire.com/api/characters/148","https://anapioficeandfire.com/api/characters/208")
    )
    val booksRepo = BooksRepository(FakeBookApi(listOf(book1,book2)))
    val pagedBooksUseCase =  PagedBooksUseCase(queryBooksUseCase = QueryBooksUseCase(booksRepo))

    @Before
    fun setUp() {
        booksViewModel = BooksViewModel(pagedBooksUseCase)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun givenViewModelWhenThenApiIsCalledThenDataIsReceived(){
        runBlocking {
            booksRepo.queryBooks(1,10).apply {
                this as ApiResponse.Success
                assertEquals(2,this.data?.size)
                assertEquals("A Game of Thrones", this.data?.first()?.name)
            }
        }
    }

    @Test
    fun givenBooksViewModelWhenHappyPaththenfirstbookshouldberetrieved() {
        val adapter1 = BooksListAdapter()
        val adapter2 = BooksListAdapter()
        runBlocking{
            booksViewModel.getBooks()

            val job = async  {
                booksViewModel.pagedBooks.take(1).toList()
            }
            val pagedData = job.await()

            adapter1.submitData(PagingData.from(listOf(book1,book2).map { it.toUi() }))
            adapter2.submitData(pagedData[0])

            assertEquals(adapter1.snapshot().size, adapter2.snapshot().size)
            assertEquals(adapter1.snapshot()[0]?.name, adapter2.snapshot()[0]?.name)
        }

    }

    @Test
    fun getBooks() {
        runBlocking {
            booksViewModel.getBooks()
            booksViewModel.pagedBooks.collectLatest {

            }
        }
    }
}