package com.zatec.features.houses.data

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.zatec.features.houses.persistence.HouseData
import kotlinx.coroutines.flow.Flow

@Dao
interface HouseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg events: HouseData)

    @Transaction
    @Query("SELECT * FROM HouseData")
    fun getHouses(): Flow<List<HouseData>>

    @Transaction
    @Query("SELECT * FROM HouseData")
    fun getPagedHouses(): PagingSource<Int, HouseData>

    @Transaction
    @Query("SELECT * FROM HouseData WHERE id IS :houseId LIMIT 1")
    fun getHouseById(houseId: String): Flow<HouseData>
}