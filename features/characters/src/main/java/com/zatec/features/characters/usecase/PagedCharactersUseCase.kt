package com.zatec.features.characters.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.zatec.features.characters.data.CharacterPagingSource
import com.zatec.features.characters.ui.CharacterUi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PagedCharactersUseCase @Inject constructor(
    private val queryCharactersUseCase: QueryCharactersUseCase
) {
    fun invoke(size: Int, page: Int): Flow<PagingData<CharacterUi>> {
        val config = PagingConfig(
            pageSize = size,
            prefetchDistance = 3,
            enablePlaceholders = true
        )
        return Pager(config) {
            CharacterPagingSource(queryCharactersUseCase, page = page, size = size)
        }.flow
    }
}