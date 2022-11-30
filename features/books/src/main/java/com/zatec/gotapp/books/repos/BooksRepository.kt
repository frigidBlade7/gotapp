package com.zatec.gotapp.books.repos

import com.zatec.gotapp.books.api.BooksApi
import com.zatec.gotapp.books.data.BookResponse
import com.zatec.gotapp.core.api.ApiResponse
import javax.inject.Inject

class BooksRepository @Inject constructor(
    private val booksApi: BooksApi
): BooksRepo{
    override suspend fun queryBooks(page: Int?, size: Int): ApiResponse<List<BookResponse>> =
        booksApi.queryBooks(page = page, pageSize = size)


    override suspend fun getBookById(bookId: Int): ApiResponse<BookResponse> =
        booksApi.getBookById(bookId)


    override fun getBookFromCache(bookId: Int) {
        TODO("Not yet implemented")
    }

}