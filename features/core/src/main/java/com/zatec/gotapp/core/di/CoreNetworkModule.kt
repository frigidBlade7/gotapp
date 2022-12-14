package com.zatec.gotapp.core.di

import android.content.Context
import com.squareup.moshi.Moshi
import com.zatec.gotapp.core.api.ApiResponseCallAdapterFactory
import com.zatec.gotapp.core.api.HeaderInterceptor
import com.zatec.gotapp.core.utils.IOContext
import com.zatec.gotapp.core.utils.NetworkConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

/**
 * Core network module
 * Injects networking components as singleton
 * @constructor Create empty Core network module
 */
@InstallIn(SingletonComponent::class)
@Module
class CoreNetworkModule {

    /**
     * Provides http request cache
     *
     * @param context is the application context
     * @return
     */
    @Singleton
    @Provides
    fun providesHttpRequestCache(@ApplicationContext context: Context): Cache {
        val cacheSize = 5 * 1024 * 1024 // 5 MB
        return Cache(File.createTempFile("http_cache",null,context.cacheDir), cacheSize.toLong())
    }

    /**
     * Provides http logger
     * prints http request response data to Logcat
     * @return
     */
    @Singleton
    @Provides
    fun providesHttpLogger(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }
        return httpLoggingInterceptor
    }

    /**
     * Provides http client
     *
     * @param httpLoggingInterceptor
     * @param cache
     * @return
     */
    @Singleton
    @Provides
    fun providesHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor, cache: Cache): OkHttpClient {
        val client = OkHttpClient.Builder().apply {
            callTimeout(NetworkConstants.API_TIMEOUT, TimeUnit.SECONDS)
            addInterceptor(HeaderInterceptor())
            addInterceptor(httpLoggingInterceptor)
            cache(cache)
        }.build()

        return client
    }

    /**
     * Provides moshi for json parsing
     * @return
     */
    @Provides
    @Singleton
    fun providesMoshi(): Moshi{
        return Moshi.Builder().build()
    }

    /**
     * Provides retrofit as our http request-response client
     *
     * @param okHttpClient
     * @param moshi
     * @return
     */
    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
        val retrofitBuilder: Retrofit.Builder = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://anapioficeandfire.com")
            .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
            .addCallAdapterFactory(ApiResponseCallAdapterFactory())

        return retrofitBuilder.build()
    }

    /**
     * Provides coroutine context
     *
     * @return
     */
    @Provides
    @Singleton
    @IOContext
    fun providesCoroutineContext(): CoroutineContext{
        return Dispatchers.IO
    }

}