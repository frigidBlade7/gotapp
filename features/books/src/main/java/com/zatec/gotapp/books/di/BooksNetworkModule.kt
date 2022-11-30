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

@InstallIn(SingletonComponent::class)
@Module
class BooksNetworkModule {

    @Singleton
    @Provides
    fun providesBooksApiService(retrofit: Retrofit): BooksApi {
        return retrofit.create(BooksApi::class.java)
    }

    @Singleton
    @Provides
    fun providesBooksRepo(booksApi: BooksApi): BooksRepo {
        return BooksRepository(booksApi)
    }
}