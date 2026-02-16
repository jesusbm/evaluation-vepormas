package com.ruskiikot.vepormasevaluacion.showdetails.data.repository

import com.ruskiikot.vepormasevaluacion.showdetails.data.datasource.ShowDetailsRemoteDataSource
import com.ruskiikot.vepormasevaluacion.showdetails.domain.model.ShowDetails
import com.ruskiikot.vepormasevaluacion.showdetails.domain.repository.ShowDetailsRepository
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class ShowDetailsRepositoryImpl @Inject constructor(
    val showDetailsRemoteDataSource: ShowDetailsRemoteDataSource,
) : ShowDetailsRepository {

    private val cache = mutableMapOf<Long, ShowDetails>()

    override suspend fun getShowDetails(showId: Long): ShowDetails {
        val cached = cache[showId]
        if (cached != null) {
            return cached
        }
        val showResult = coroutineScope {
            showDetailsRemoteDataSource.getShowDetails(showId)
        }
        println(showResult)
        return showResult.fold(
            onSuccess = { it },
            onFailure = { throw it },
        )
    }
}
