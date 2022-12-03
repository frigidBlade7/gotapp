package com.zatec.gotapp.books.di

import com.zatec.gotapp.books.api.BooksApi
import com.zatec.gotapp.books.repos.BooksRepo
import com.zatec.gotapp.books.repos.BooksRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Books network module
 *  module for injecting network components
 * @constructor Create empty Books network module
 */
@InstallIn(SingletonComponent::class)
@Module
class BooksNetworkModule {

    /**
     * Provides books api service
     *
     * @param retrofit http rest client
     * @return
     */
    @Singleton
    @Provides
    fun providesBooksApiService(retrofit: Retrofit): BooksApi {
        return retrofit.create(BooksApi::class.java)
    }

    /**
     * Provides books repo
     *
     * @param booksApi
     * @return
     */
    @Singleton
    @Provides
    fun providesBooksRepo(booksApi: BooksApi): BooksRepo {
        return BooksRepository(booksApi)
    }
}