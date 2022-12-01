package com.zatec.features.houses.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.zatec.gotapp.core.data.StringListConverter

@Database(entities = [HouseDao::class], version = 1,
    autoMigrations = [],
    exportSchema = true)
@TypeConverters(StringListConverter::class)
abstract class HouseDatabase: RoomDatabase(){
    abstract fun houseDao(): HouseDao
}