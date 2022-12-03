package com.zatec.features.houses.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.zatec.features.houses.persistence.HouseData
import com.zatec.gotapp.core.data.StringListConverter

/**
 * House database
 *
 * @constructor Create empty House database
 */
@Database(entities = [HouseData::class], version = 1,
    autoMigrations = [],
    exportSchema = true)
@TypeConverters(StringListConverter::class)
abstract class HouseDatabase: RoomDatabase(){
    /**
     * House dao
     *
     * @return interface to interact CRUD on HouseDatabase
     */
    abstract fun houseDao(): HouseDao
}