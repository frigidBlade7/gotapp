package com.zatec.gotapp.books

import android.net.Uri
import com.zatec.gotapp.books.api.BooksApi
import com.zatec.gotapp.books.data.BookResponse
import com.zatec.gotapp.core.api.ApiResponse

class FakeBookApi(private val books: List<BookResponse>? = listOf()): BooksApi {
    override suspend fun queryBooks(page: Int?, pageSize: Int?): ApiResponse<List<BookResponse>> =
        ApiResponse.Success(books, page)

    override suspend fun getBookById(bookId: Int?): ApiResponse<BookResponse> =
        ApiResponse.Success(books?.find { Uri.parse(it.url).lastPathSegment?.toInt() == bookId })
}