package com.zatec.gotapp.books.api

import com.zatec.gotapp.books.api.Routes.api
import com.zatec.gotapp.books.api.Routes.bookId
import com.zatec.gotapp.books.api.Routes.books
import com.zatec.gotapp.books.data.BookResponse
import com.zatec.gotapp.core.api.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Books api
 * retrofit type safe interface for http calls
 * @constructor Create empty Books api
 */
interface BooksApi {

    /**
     * Query books
     * request api to return a list of Books
     * @param page the page number for the request
     * @param pageSize max number of items in a page
     * @return
     */
    @GET("$api$books")
    suspend fun queryBooks(
        @Query(QueryParams.page) page: Int?,
        @Query(QueryParams.pageSize) pageSize: Int?
    ): ApiResponse<List<BookResponse>>

    /**
     * Get book by id
     * api function to request book by its id stored in room
     * @param bookId the id of the book to be passed in the path of the request
     * @return
     */
    @GET("$api$books$bookId")
    suspend fun getBookById(
        @Path(Routes.bookId) bookId: Int?
    ): ApiResponse<BookResponse>

}

object Routes {
    const val api = "/api"
    const val books = "/books"
    const val bookId = "/{bookId}"
}

object QueryParams{
    const val page = "page"
    const val pageSize = "pageSize"
}