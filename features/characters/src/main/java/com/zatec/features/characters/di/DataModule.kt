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


@InstallIn(SingletonComponent::class)
@Module
class DataModule {
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
    fun providesBookDao(database: CharacterDatabase): CharacterDao {
        return database.characterDao()
    }
}