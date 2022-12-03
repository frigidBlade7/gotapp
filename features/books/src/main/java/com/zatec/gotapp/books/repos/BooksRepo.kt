package com.zatec.gotapp.books.repos

import com.zatec.gotapp.books.data.BookResponse
import com.zatec.gotapp.core.api.ApiResponse

/**
 * Books repo interface to allow for loose coupling of repository components
 *
 * @constructor Create empty Books repo
 */
interface BooksRepo {
    /**
     * Query books
     *
     * @param page page number for api request
     * @param size number of books per page
     * @return
     */
    suspend fun queryBooks(page: Int?, size: Int): ApiResponse<List<BookResponse>>

    /**
     * Get book by id
     * return book from api
     * @param bookId
     * @return
     */
    suspend fun getBookById(bookId: Int): ApiResponse<BookResponse>

    /**
     * Get book from cache
     * return book from room db
     * @param bookId
     */
    fun getBookFromCache(bookId: Int)
}
