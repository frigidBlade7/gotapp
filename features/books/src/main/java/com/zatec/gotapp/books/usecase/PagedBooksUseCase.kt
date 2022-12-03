package com.zatec.gotapp.books.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.zatec.gotapp.books.data.BooksPagingSource
import com.zatec.gotapp.books.ui.BookUi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Paged books use case
 *
 * @property queryBooksUseCase inject use case for fetching books page from api
 * @constructor Create empty Paged books use case
 */
class PagedBooksUseCase @Inject constructor(
    private val queryBooksUseCase: QueryBooksUseCase
) {
    /**
     * Invoke
     *
     * @param size number of items per page
     * @param page page number
     * @return
     */
    fun invoke(size: Int, page: Int): Flow<PagingData<BookUi>> {
        val config = PagingConfig(
            pageSize = size,
            prefetchDistance = 1,
            enablePlaceholders = true
        )
        return Pager(config) {
            BooksPagingSource(queryBooksUseCase, page = page, size = size)
        }.flow
    }
}