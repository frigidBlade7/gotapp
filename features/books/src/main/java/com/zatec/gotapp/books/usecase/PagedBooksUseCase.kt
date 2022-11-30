package com.zatec.gotapp.books.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.zatec.gotapp.books.data.BooksPagingSource
import com.zatec.gotapp.books.ui.BookUi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PagedBooksUseCase @Inject constructor(
    private val queryBooksUseCase: QueryBooksUseCase
) {
    fun invoke(size: Int, page: Int): Flow<PagingData<BookUi>> {
        val config = PagingConfig(
            pageSize = size,
            prefetchDistance = 2,
            enablePlaceholders = true
        )
        return Pager(config) {
            BooksPagingSource(queryBooksUseCase, page = page, size = size)
        }.flow
    }
}