package com.zatec.features.houses.di

import com.zatec.features.houses.api.HousesApi
import com.zatec.features.houses.repos.HousesRepo
import com.zatec.features.houses.repos.HousesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class HousesNetworkModule {

    @Singleton
    @Provides
    fun providesHousesApiService(retrofit: Retrofit): HousesApi {
        return retrofit.create(HousesApi::class.java)
    }

    @Singleton
    @Provides
    fun providesHousesRepo(booksApi: HousesApi): HousesRepo {
        return HousesRepository(booksApi)
    }
}