package com.zatec.features.characters.repos

import com.zatec.features.characters.api.CharactersApi
import com.zatec.features.characters.data.CharacterResponse
import com.zatec.gotapp.core.api.ApiResponse
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val charactersApi: CharactersApi
): CharactersRepo {
    override suspend fun queryCharacters(page: Int?, size: Int): ApiResponse<List<CharacterResponse>> =
        charactersApi.queryCharacters(page = page, pageSize = size)


    override suspend fun getCharacterById(characterId: Int): ApiResponse<CharacterResponse> =
        charactersApi.getCharacterById(characterId)


    override fun getCharacterFromCache(houseId: Int) {
        TODO("Not yet implemented")
    }

}