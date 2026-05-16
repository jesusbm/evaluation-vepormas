package com.ruskiikot.vepormasevaluacion.di

import com.ruskiikot.vepormasevaluacion.showdetails.data.repository.ShowDetailsRepositoryImpl
import com.ruskiikot.vepormasevaluacion.showdetails.data.datasource.ShowDetailsRemoteDataSource
import com.ruskiikot.vepormasevaluacion.showdetails.data.datasource.impl.ShowDetailsRemoteDataSourceImpl
import com.ruskiikot.vepormasevaluacion.showdetails.domain.repository.ShowDetailsRepository
import com.ruskiikot.vepormasevaluacion.showdetails.domain.usecase.LoadShowDetailsUseCase
import com.ruskiikot.vepormasevaluacion.showdetails.presentation.ShowDetailsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val showDetailsModule = module {

    single<ShowDetailsRepository> {
        ShowDetailsRepositoryImpl(
            showDetailsRemoteDataSource = get(),
        )
    }

    single<ShowDetailsRemoteDataSource> {
        ShowDetailsRemoteDataSourceImpl(
            apiClient = get(),
        )
    }

    single {
        LoadShowDetailsUseCase(
            showDetailsRepository = get(),
        )
    }

    viewModelOf(::ShowDetailsViewModel)
}
