package com.zatec.features.characters.repos

import com.zatec.features.characters.api.CharactersApi
import com.zatec.features.characters.data.CharacterDao
import com.zatec.features.characters.data.CharacterResponse
import com.zatec.features.characters.persistence.CharacterData
import com.zatec.gotapp.core.api.ApiResponse
import com.zatec.gotapp.core.utils.IOContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext


/**
 * Character repository
 * implementation of [CharactersRepo] to provide CRUD interactions with api and db
 * @property charactersApi api for fetching character info
 * @property database room db to cache responses
 * @constructor Create empty Character repository
 */
class CharacterRepository @Inject constructor(
    private val charactersApi: CharactersApi,
    private val characterDao: CharacterDao,
    @IOContext val coroutineContext: CoroutineContext
): CharactersRepo {
    override suspend fun queryCharacters(page: Int?, size: Int): ApiResponse<List<CharacterResponse>> =
        charactersApi.queryCharacters(page = page, pageSize = size)


    override suspend fun getCharacterById(characterId: Int): ApiResponse<CharacterResponse> =
        charactersApi.getCharacterById(characterId)


    override fun getCharacterFromCache(characterId: String): Flow<CharacterData?> {
        return characterDao.getCharacterById(characterId)
    }

    override suspend fun clearCharacterCache() {
        withContext(coroutineContext){
            characterDao.clear()
        }
    }

    override suspend fun insertCharacters(vararg characters: CharacterData) {
        withContext(coroutineContext){
            characterDao.insert(*characters)
        }
    }

}