package com.zatec.gotapp.books.viewmodels

import com.zatec.gotapp.books.FakeBookApi
import com.zatec.gotapp.books.repos.BooksRepo
import com.zatec.gotapp.books.repos.BooksRepository
import com.zatec.gotapp.books.usecase.PagedBooksUseCase
import com.zatec.gotapp.books.usecase.QueryBooksUseCase
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

class BooksViewModelTest {

    private val booksRepo = BooksRepository(FakeBookApi())
    private val pagedBooksUseCase =  PagedBooksUseCase(queryBooksUseCase = QueryBooksUseCase(booksRepo))
    private val booksViewModel = BooksViewModel(pagedBooksUseCase)

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getPagedBooks() {
    }

    @Test
    fun getBooks() {
    }
}