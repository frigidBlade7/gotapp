package com.zatec.features.characters

import androidx.paging.PagingSource
import com.zatec.features.characters.data.CharacterDao
import com.zatec.features.characters.data.CharacterResponse
import com.zatec.features.characters.persistence.CharacterData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeCharactersDao(
    private val characters: List<CharacterResponse>? = listOf()
): CharacterDao {
    override fun insert(vararg character: CharacterData) {
        TODO("Not yet implemented")
    }

    override fun getCharacter(): Flow<List<CharacterData>> = flow {
        emit(characters?.map { it.toData() }?: listOf())
    }

    override fun getPagedCharacters(): PagingSource<Int, CharacterData> {
        TODO("Not yet implemented")
    }

    override fun getCharacterById(characterId: String): Flow<CharacterData?> = flow {
        emit(characters?.map { it.toData() }?.find { it.id == characterId })
    }

    override fun clear() {
        TODO("Not yet implemented")
    }
}