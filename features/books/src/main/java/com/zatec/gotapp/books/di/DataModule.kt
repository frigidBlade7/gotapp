package com.zatec.gotapp.books.di

import android.content.Context
import androidx.room.Room
import com.zatec.gotapp.books.data.BookDao
import com.zatec.gotapp.books.data.BookDatabase
import com.zatec.gotapp.core.data.StringListConverter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/**
 * Books network module
 *  module for injecting database components
 * @constructor Create Books data module
 */
@InstallIn(SingletonComponent::class)
@Module
object DataModule {
    @Singleton
    @Provides
    fun providesDatabase(@ApplicationContext context: Context,
                         stringListConverter: StringListConverter
    ): BookDatabase {
        return Room.databaseBuilder(
            context,
            BookDatabase::class.java, "BOOKS_DB"
        )
            .addTypeConverter(stringListConverter)
            .fallbackToDestructiveMigration()
            .fallbackToDestructiveMigrationOnDowngrade()
            .build()
    }

    @Singleton
    @Provides
    fun providesBookDao(database: BookDatabase): BookDao {
        return database.bookDao()
    }
}