package com.ruskiikot.vepormasevaluacion.showdetails.data.datasource.impl

import com.ruskiikot.vepormasevaluacion.domain.exception.EmptyContentRemoteException
import com.ruskiikot.vepormasevaluacion.domain.exception.GeneralRemoteException
import com.ruskiikot.vepormasevaluacion.showdetails.domain.model.ShowDetails
import com.ruskiikot.vepormasevaluacion.showdetails.data.datasource.ShowDetailsRemoteDataSource
import com.ruskiikot.vepormasevaluacion.network.TvMazeApiClient
import com.ruskiikot.vepormasevaluacion.showdetails.data.mapper.toDomain
import javax.inject.Inject

class ShowDetailsRemoteDataSourceImpl @Inject constructor(
    val tvMazeApiClient: TvMazeApiClient,
) : ShowDetailsRemoteDataSource {

    override suspend fun getShowDetails(showId: Long): Result<ShowDetails> {
        return try {
            val response = tvMazeApiClient.getShow(showId)
            val content = response.body()
            when  {
                response.isSuccessful && content != null ->
                    Result.success(content.toDomain())

                content == null ->
                    Result.failure(EmptyContentRemoteException())

                else ->
                    Result.failure(GeneralRemoteException("code: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
