package com.zatec.features.houses.data

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.zatec.features.houses.persistence.HouseData
import kotlinx.coroutines.flow.Flow


/**
 * House dao
 * data access object for house
 * @constructor Create empty House dao
 */
@Dao
interface HouseDao {
    /**
     * Insert
     *
     * @param houses single or list of house(s) to insert into db
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg houses: HouseData)

    /**
     * Get houses
     *
     * @return all houses in a list wrapped in a [Flow]
     */
    @Transaction
    @Query("SELECT * FROM HouseData")
    fun getHouses(): Flow<List<HouseData>>

    /**
     * Get paged houses
     *
     * @return a [PagingSource] of [HouseData]
     */
    @Transaction
    @Query("SELECT * FROM HouseData")
    fun getPagedHouses(): PagingSource<Int, HouseData>

    /**
     * Get house by id
     *
     * @param houseId
     * @return [HouseData] object wrapped in a [Flow]
     */
    @Transaction
    @Query("SELECT * FROM HouseData WHERE id IS :houseId LIMIT 1")
    fun getHouseById(houseId: String): Flow<HouseData>
}