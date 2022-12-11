package com.zatec.features.characters.data

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.zatec.features.characters.persistence.CharacterData
import kotlinx.coroutines.flow.Flow

/**
 * Character dao
 * data access object to fetch cached character data
 * @constructor Create empty Character dao
 */
@Dao
interface CharacterDao {
    /**
     * Insert
     * add a single character or list of characters to character room db
     * @param character
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg character: CharacterData)

    /**
     * Get character
     * return a list of characters cached in room db
     * @return
     */
    @Transaction
    @Query("SELECT * FROM CharacterData")
    fun getCharacter(): Flow<List<CharacterData>>

    /**
     * Get paged characters
     *
     * @return a list of characters cached in room db as a PagingSource
     */
    @Transaction
    @Query("SELECT * FROM CharacterData")
    fun getPagedCharacters(): PagingSource<Int, CharacterData>

    /**
     * Get character by id
     * return a specific character cached in room db
     * @param characterId
     * @return
     */
    @Transaction
    @Query("SELECT * FROM CharacterData WHERE id IS :characterId LIMIT 1")
    fun getCharacterById(characterId: String): Flow<CharacterData?>

    /**
     * Clear
     * remove all [CharacterData] objects from the character table
     */
    @Query("DELETE FROM CharacterData")
    fun clear()
}