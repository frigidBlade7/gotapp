package com.zatec.gotapp.books.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.zatec.gotapp.books.persistence.BookData
import com.zatec.gotapp.core.data.StringListConverter

@Database(entities = [BookData::class], version = 1,
    autoMigrations = [],
    exportSchema = true)
@TypeConverters(StringListConverter::class)
abstract class BookDatabase: RoomDatabase(){
    abstract fun bookDao(): BookDao
}