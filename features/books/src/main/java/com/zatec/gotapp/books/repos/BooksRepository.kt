package com.zatec.gotapp.books.repos

import com.zatec.gotapp.books.api.BooksApi
import com.zatec.gotapp.books.data.BookResponse
import com.zatec.gotapp.core.api.ApiResponse
import com.zatec.gotapp.core.utils.IOContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BooksRepository @Inject constructor(
    private val booksApi: BooksApi,
    @IOContext private val ioDispatcher: CoroutineDispatcher
): BooksRepo{
    override suspend fun queryBooks(page: Int?, size: Int): ApiResponse<List<BookResponse>> = withContext(ioDispatcher){
        booksApi.queryBooks(page = page, pageSize = size)
    }

    override suspend fun getBookById(bookId: Int): ApiResponse<BookResponse> = withContext(ioDispatcher){
        booksApi.getBookById(bookId)
    }

    override fun getBookFromCache(bookId: Int) {
        TODO("Not yet implemented")
    }

}