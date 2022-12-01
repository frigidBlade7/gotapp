package com.zatec.features.characters.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.zatec.features.characters.persistence.CharacterData
import com.zatec.gotapp.core.data.StringListConverter

@Database(entities = [CharacterData::class], version = 1,
    autoMigrations = [],
    exportSchema = true)
@TypeConverters(StringListConverter::class)
abstract class CharacterDatabase: RoomDatabase(){
    abstract fun characterDao(): CharacterDao
}