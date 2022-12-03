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

/**
 * Houses network module
 * module for injecting network components
 * @constructor Create empty Houses network module
 */
@InstallIn(SingletonComponent::class)
@Module
class HousesNetworkModule {

    /**
     * Provides houses api service
     *
     * @param retrofit
     * @return  [HousesApi] to send http requests
     */
    @Singleton
    @Provides
    fun providesHousesApiService(retrofit: Retrofit): HousesApi {
        return retrofit.create(HousesApi::class.java)
    }

    /**
     * Provides houses repo
     *
     * @param booksApi
     * @return [HousesRepository] as an instance of [HousesRepo]
     */
    @Singleton
    @Provides
    fun providesHousesRepo(booksApi: HousesApi): HousesRepo {
        return HousesRepository(booksApi)
    }
}