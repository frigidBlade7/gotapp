package com.zatec.features.characters.usecase

import com.zatec.features.characters.repos.CharactersRepo
import com.zatec.gotapp.core.utils.flowResult
import javax.inject.Inject

class QueryCharactersUseCase @Inject constructor(
    private val charactersRepo: CharactersRepo
) {
    operator fun invoke(page: Int = 1, size: Int = 50) = flowResult {
        charactersRepo.queryCharacters(page, size)
    }
}