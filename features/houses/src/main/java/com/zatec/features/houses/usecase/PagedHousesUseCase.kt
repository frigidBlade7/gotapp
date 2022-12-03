package com.zatec.features.houses.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.zatec.features.houses.data.HousesPagingSource
import com.zatec.features.houses.ui.HouseUi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Paged houses use case
 * paged data using [queryHousesUseCase] for api calls
 * @property queryHousesUseCase
 * @constructor Create empty Paged houses use case
 */
class PagedHousesUseCase @Inject constructor(
    private val queryHousesUseCase: QueryHousesUseCase
) {
    /**
     * Invoke
     *
     * @param size number of items per page
     * @param page page number of load
     * @return
     */
    fun invoke(size: Int, page: Int): Flow<PagingData<HouseUi>> {
        val config = PagingConfig(
            pageSize = size,
            prefetchDistance = 1,
            enablePlaceholders = true
        )
        return Pager(config) {
            HousesPagingSource(queryHousesUseCase, page = page, size = size)
        }.flow
    }
}