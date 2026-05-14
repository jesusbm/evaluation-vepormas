package com.ruskiikot.vepormasevaluacion.di

import com.ruskiikot.vepormasevaluacion.showdetails.data.repository.ShowDetailsRepositoryImpl
import com.ruskiikot.vepormasevaluacion.showdetails.data.datasource.ShowDetailsRemoteDataSource
import com.ruskiikot.vepormasevaluacion.showdetails.data.datasource.impl.ShowDetailsRemoteDataSourceImpl
import com.ruskiikot.vepormasevaluacion.showdetails.domain.repository.ShowDetailsRepository

interface ShowDetailsModule {

    fun getShowDetailsRepository(impl: ShowDetailsRepositoryImpl): ShowDetailsRepository

    fun getShowDetailsRemoteDataSource(impl: ShowDetailsRemoteDataSourceImpl): ShowDetailsRemoteDataSource
}
