package com.zatec.gotapp.books.usecase

import com.zatec.gotapp.books.repos.BooksRepo
import com.zatec.gotapp.core.utils.flowResult
import javax.inject.Inject

class QueryBooksUseCase @Inject constructor(
    private val booksRepo: BooksRepo
) {
    operator fun invoke(page: Int = 1, size: Int = 50) = flowResult {
        booksRepo.queryBooks(page, size)
    }
}