package com.example.simplemvvm.core_feature.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Url
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AppModule {
    companion object{
        private const val TIME_OUT = 1L
        private const val URL_BASE="https://jsonplaceholder.typicode.com/"
        @Provides
        @Singleton
        fun provideRetrofit(
            okHttpClient: OkHttpClient
        ):Retrofit = Retrofit
            .Builder()
            .baseUrl(URL_BASE)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        @Provides
        @Singleton
        fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
            .callTimeout(TIME_OUT, TimeUnit.MINUTES)
            .connectTimeout(TIME_OUT, TimeUnit.MINUTES)
            .addNetworkInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }
}