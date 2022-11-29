package com.zatec.gotapp.core.di

import com.squareup.moshi.Moshi
import com.zatec.gotapp.core.api.ApiFlowCallAdapterFactory
import com.zatec.gotapp.core.utils.NetworkConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class CoreNetworkModule {

    @Singleton
    @Provides
    fun providesHttpLogger(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }
        return httpLoggingInterceptor
    }

    @Singleton
    @Provides
    fun providesHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val client = OkHttpClient.Builder().apply {
            callTimeout(NetworkConstants.API_TIMEOUT, TimeUnit.SECONDS)
            addInterceptor(httpLoggingInterceptor)
        }.build()

        return client
    }

    @Provides
    @Singleton
    fun providesMoshi(): Moshi{
        return Moshi.Builder().build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit{
        val retrofitBuilder: Retrofit.Builder = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://anapioficeandfire.com")
            .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
            .addCallAdapterFactory(ApiFlowCallAdapterFactory())

        return retrofitBuilder.build()
    }
}