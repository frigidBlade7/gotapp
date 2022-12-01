package com.zatec.features.characters.repos

import com.zatec.features.characters.data.CharacterResponse
import com.zatec.gotapp.core.api.ApiResponse

interface CharactersRepo {
    suspend fun queryCharacters(page: Int?, size: Int): ApiResponse<List<CharacterResponse>>
    suspend fun getCharacterById(characterId: Int): ApiResponse<CharacterResponse>

    fun getCharacterFromCache(characterId: Int)
}
