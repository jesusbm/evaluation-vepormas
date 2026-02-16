package com.ruskiikot.vepormasevaluacion.di

import com.ruskiikot.vepormasevaluacion.network.TvMazeApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun TvMazeApiClient(): TvMazeApiClient {
        return getBasicRetrofitInstance()
            .create(TvMazeApiClient::class.java)
    }

    private fun getBasicRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.tvmaze.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(basicOkHttpClient())
            .build()
    }

    private fun basicOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply { level = Level.BODY })
            .build()
    }
}
