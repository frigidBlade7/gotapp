package com.zatec.gotapp.books.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.zatec.gotapp.books.persistence.BookData
import com.zatec.gotapp.core.data.StringListConverter

/**
 * Book database
 * db instance for holding BookData objects
 * @constructor Create empty Book database
 */
@Database(entities = [BookData::class], version = 1,
    autoMigrations = [],
    exportSchema = true)
@TypeConverters(StringListConverter::class)
abstract class BookDatabase: RoomDatabase(){
    /**
     * Book dao
     * data access object for CRUD requests on book Database
     * @return
     */
    abstract fun bookDao(): BookDao
}