package com.zatec.features.characters.repos

import com.zatec.features.characters.data.CharacterResponse
import com.zatec.features.characters.persistence.CharacterData
import com.zatec.gotapp.core.api.ApiResponse
import kotlinx.coroutines.flow.Flow

/**
 * Characters repo
 * repository interface for RUD operations
 * @constructor Create empty Characters repo
 */
interface CharactersRepo {
    /**
     * Query characters
     *
     * @param page page number of items to be loaded
     * @param size number of items per page
     * @return
     */
    suspend fun queryCharacters(page: Int?, size: Int): ApiResponse<List<CharacterResponse>>

    /**
     * Get character by id
     *
     * @param characterId
     * @return specific character with id [characterId] from api
     */
    suspend fun getCharacterById(characterId: Int): ApiResponse<CharacterResponse>

    /**
     * Get character from cache
     *
     * @param characterId
     * @return specific character with id [characterId] from database
     * */
    fun getCharacterFromCache(characterId: String): Flow<CharacterData>

    fun clearCharacterCache()
}
