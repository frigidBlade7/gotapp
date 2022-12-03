package com.zatec.features.characters.di

import com.zatec.features.characters.api.CharactersApi
import com.zatec.features.characters.data.CharacterDatabase
import com.zatec.features.characters.repos.CharacterRepository
import com.zatec.features.characters.repos.CharactersRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


/**
 * Characters network module
 * module for injecting networking dependencies
 * @constructor Create empty Characters network module
 */
@InstallIn(SingletonComponent::class)
@Module
class CharactersNetworkModule {

    /**
     * Provides characters api service
     * rest api client
     * @param retrofit
     * @return
     */
    @Singleton
    @Provides
    fun providesCharactersApiService(retrofit: Retrofit): CharactersApi {
        return retrofit.create(CharactersApi::class.java)
    }

    /**
     * Provides characters repo
     * repository for interacting with rest api and database
     * @param charactersApi rest api client
     * @param database room database to cache data
     * @return
     */
    @Singleton
    @Provides
    fun providesCharactersRepo(charactersApi: CharactersApi, database: CharacterDatabase): CharactersRepo {
        return CharacterRepository(charactersApi, database)
    }
}