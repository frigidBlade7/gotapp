package com.zatec.features.characters.usecase

import com.zatec.features.characters.repos.CharactersRepo
import com.zatec.gotapp.core.utils.flowResult
import javax.inject.Inject

/**
 * Query characters use case
 *
 * @property charactersRepo repository for requesting
 * @constructor Create empty Query characters use case
 */
class QueryCharactersUseCase @Inject constructor(
    private val charactersRepo: CharactersRepo
) {
    /**
     * Invoke
     *
     * @param page page number of items to be loaded
     * @param size number of items per page
     */
    operator fun invoke(page: Int = 1, size: Int = 50) = flowResult {
        charactersRepo.queryCharacters(page, size)
    }
}