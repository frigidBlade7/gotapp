package com.zatec.features.houses.di

import com.zatec.features.houses.api.HousesApi
import com.zatec.features.houses.data.HouseDao
import com.zatec.features.houses.data.HouseDatabase
import com.zatec.features.houses.repos.HousesRepo
import com.zatec.features.houses.repos.HousesRepository
import com.zatec.gotapp.core.utils.IOContext
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

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
    fun providesHousesRepo(booksApi: HousesApi, houseDao: HouseDao, @IOContext coroutineContext: CoroutineContext): HousesRepo {
        return HousesRepository(booksApi, houseDao, coroutineContext)
    }
}