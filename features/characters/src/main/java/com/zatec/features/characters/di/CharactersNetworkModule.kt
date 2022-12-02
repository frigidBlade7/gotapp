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

@InstallIn(SingletonComponent::class)
@Module
class CharactersNetworkModule {

    @Singleton
    @Provides
    fun providesCharactersApiService(retrofit: Retrofit): CharactersApi {
        return retrofit.create(CharactersApi::class.java)
    }

    @Singleton
    @Provides
    fun providesCharactersRepo(charactersApi: CharactersApi, database: CharacterDatabase): CharactersRepo {
        return CharacterRepository(charactersApi, database)
    }
}