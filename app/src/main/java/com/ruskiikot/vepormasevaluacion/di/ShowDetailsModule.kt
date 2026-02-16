package com.ruskiikot.vepormasevaluacion.di

import com.ruskiikot.vepormasevaluacion.showdetails.data.repository.ShowDetailsRepositoryImpl
import com.ruskiikot.vepormasevaluacion.showdetails.data.datasource.ShowDetailsRemoteDataSource
import com.ruskiikot.vepormasevaluacion.showdetails.data.datasource.impl.ShowDetailsRemoteDataSourceImpl
import com.ruskiikot.vepormasevaluacion.showdetails.domain.repository.ShowDetailsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface ShowDetailsModule {

    @Singleton
    @Binds
    fun getShowDetailsRepository(impl: ShowDetailsRepositoryImpl): ShowDetailsRepository

    @Binds
    fun getShowDetailsRemoteDataSource(impl: ShowDetailsRemoteDataSourceImpl): ShowDetailsRemoteDataSource
}
