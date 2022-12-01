package com.zatec.features.houses.di

import android.content.Context
import androidx.room.Room
import com.zatec.features.houses.data.HouseDao
import com.zatec.features.houses.data.HouseDatabase
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
    ): HouseDatabase {
        return Room.databaseBuilder(
            context,
            HouseDatabase::class.java, "HOUSES_DB"
        )
            .addTypeConverter(stringListConverter)
            .fallbackToDestructiveMigration()
            .fallbackToDestructiveMigrationOnDowngrade()
            .build()
    }

    @Singleton
    @Provides
    fun providesBookDao(database: HouseDatabase): HouseDao {
        return database.houseDao()
    }
}