package com.zatec.features.characters.di

import android.content.Context
import androidx.room.Room
import com.zatec.features.characters.data.CharacterDao
import com.zatec.features.characters.data.CharacterDatabase
import com.zatec.gotapp.core.data.StringListConverter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Character data module
 *  module for injecting database components
 * @constructor Create Charater data module
 */
@InstallIn(SingletonComponent::class)
@Module
object DataModule {
    @Singleton
    @Provides
    fun providesDatabase(@ApplicationContext context: Context,
                         stringListConverter: StringListConverter
    ): CharacterDatabase {
        return Room.databaseBuilder(
            context,
            CharacterDatabase::class.java, "CHARACTERS_DB"
        )
            .addTypeConverter(stringListConverter)
            .fallbackToDestructiveMigration()
            .fallbackToDestructiveMigrationOnDowngrade()
            .build()
    }

    @Singleton
    @Provides
    fun providesCharacterDao(database: CharacterDatabase): CharacterDao {
        return database.characterDao()
    }
}