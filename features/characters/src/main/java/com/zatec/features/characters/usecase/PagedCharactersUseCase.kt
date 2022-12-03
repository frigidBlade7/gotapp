package com.zatec.features.characters.usecase

import androidx.paging.*
import com.zatec.features.characters.data.CharacterDatabase
import com.zatec.features.characters.data.CharacterRemotePagingMediatorFactory
import com.zatec.features.characters.persistence.toUi
import com.zatec.features.characters.ui.CharacterUi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Paged characters use case
 *
 * @property database room db for Character module
 * @property remoteMediatorFactory central location for storing api responses in room db
 * and fetching UiResult to display in recycler view
 * @constructor Create empty Paged characters use case
 */
class PagedCharactersUseCase @Inject constructor(
    private val database: CharacterDatabase,
    private val remoteMediatorFactory: CharacterRemotePagingMediatorFactory
) {
    /**
     * Invoke
     *
     * @param page page number of items to be loaded
     * @param size number of items per page
     * @return flow of paging data of [CharacterUi] objects, parsed from room db backed by api
     */
    @OptIn(ExperimentalPagingApi::class)
    fun invoke(size: Int, page: Int): Flow<PagingData<CharacterUi>> {
        val config = PagingConfig(
            pageSize = size,
            prefetchDistance = 3,
            enablePlaceholders = true
        )
        return Pager(
            config = config,
            pagingSourceFactory = { database.characterDao().getPagedCharacters() },
            remoteMediator = remoteMediatorFactory.create(page, size)
        ).flow.map {it.map { data -> data.toUi() }}
    }
}
