package com.zatec.gotapp.books.usecase

import com.zatec.gotapp.books.repos.BooksRepo
import com.zatec.gotapp.core.utils.flowResult
import javax.inject.Inject

/**
 * Query books use case
 *
 * @property booksRepo repository for fetching books
 * @constructor Create empty Query books use case
 */
class QueryBooksUseCase @Inject constructor(
    private val booksRepo: BooksRepo
) {
    /**
     * Invoke
     *
     * @param page page number of request
     * @param size number of items per page
     */
    operator fun invoke(page: Int = 1, size: Int = 50) = flowResult {
        booksRepo.queryBooks(page, size)
    }
}