package com.zatec.features.characters.data

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.zatec.features.characters.persistence.CharacterData
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg events: CharacterData)

    @Transaction
    @Query("SELECT * FROM CharacterData")
    fun getCharacter(): Flow<List<CharacterData>>

    @Transaction
    @Query("SELECT * FROM CharacterData")
    fun getPagedCharacters(): PagingSource<Int, CharacterData>

    @Transaction
    @Query("SELECT * FROM CharacterData WHERE id IS :characterId LIMIT 1")
    fun getCharacterById(characterId: String): Flow<CharacterData>

    @Query("DELETE FROM CharacterData")
    fun clear()
}