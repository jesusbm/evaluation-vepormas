package com.ruskiikot.vepormasevaluacion.showdetails.data.datasource.impl

import com.ruskiikot.vepormasevaluacion.network.TvMazeKtorApiClient
import com.ruskiikot.vepormasevaluacion.showdetails.data.datasource.ShowDetailsRemoteDataSource
import com.ruskiikot.vepormasevaluacion.showdetails.data.mapper.toDomain
import com.ruskiikot.vepormasevaluacion.showdetails.domain.model.ShowDetails
import javax.inject.Inject

class ShowDetailsRemoteDataSourceImpl @Inject constructor(
    val apiClient: TvMazeKtorApiClient,
) : ShowDetailsRemoteDataSource {

    override suspend fun getShowDetails(showId: Long): Result<ShowDetails> {
        return try {
            val response = apiClient.getShow(showId)
            Result.success(response.toDomain())
            // TODO: manejar adecuadamente las posibles respuestas erroneas
            /*val content = response.body()
            when  {
                response.isSuccessful && content != null ->
                    Result.success(content.toDomain())

                content == null ->
                    Result.failure(EmptyContentRemoteException())

                else ->
                    Result.failure(GeneralRemoteException("code: ${response.code()}"))
            }*/
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
