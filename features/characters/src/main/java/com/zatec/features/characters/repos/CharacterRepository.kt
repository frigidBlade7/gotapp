package com.zatec.features.characters.repos

import com.zatec.features.characters.api.CharactersApi
import com.zatec.features.characters.data.CharacterDatabase
import com.zatec.features.characters.data.CharacterResponse
import com.zatec.features.characters.persistence.CharacterData
import com.zatec.gotapp.core.api.ApiResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val charactersApi: CharactersApi,
    private val database: CharacterDatabase
): CharactersRepo {
    override suspend fun queryCharacters(page: Int?, size: Int): ApiResponse<List<CharacterResponse>> =
        charactersApi.queryCharacters(page = page, pageSize = size)


    override suspend fun getCharacterById(characterId: Int): ApiResponse<CharacterResponse> =
        charactersApi.getCharacterById(characterId)


    override fun getCharacterFromCache(characterId: String): Flow<CharacterData> {
        return database.characterDao().getCharacterById(characterId)
    }

}