package com.zatec.gotapp.books.repos

import com.zatec.gotapp.books.data.BookResponse
import com.zatec.gotapp.core.api.ApiResponse

interface BooksRepo {
    suspend fun queryBooks(page: Int?, size: Int): ApiResponse<List<BookResponse>>
    suspend fun getBookById(bookId: Int): ApiResponse<BookResponse>

    fun getBookFromCache(bookId: Int)
}
