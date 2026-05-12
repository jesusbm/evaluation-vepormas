package com.ruskiikot.vepormasevaluacion.episodes.data.datasource.impl

import com.ruskiikot.vepormasevaluacion.episodes.domain.model.Episode
import com.ruskiikot.vepormasevaluacion.episodes.data.datasource.EpisodesRemoteDataSource
import com.ruskiikot.vepormasevaluacion.episodes.data.mapper.toDomain
import com.ruskiikot.vepormasevaluacion.network.TvMazeKtorApiClient
import javax.inject.Inject

class EpisodesRemoteDataSourceImpl @Inject constructor(
    private val apiClient: TvMazeKtorApiClient,
) : EpisodesRemoteDataSource {

    override suspend fun getEpisodes(countryCode: String, date: String): Result<List<Episode>> {
        return try {
            val response = apiClient.getSchedule(countryCode, date)
            Result.success(response.toDomain())
            // TODO: manejar adecuadamente las posibles respuestas erroneas
            /*when {
                response.isSuccessful && content != null ->
                    Result.success(content.toDomain())

                content == null ->
                    Result.failure(EmptyContentRemoteException())

                else ->
                    Result.failure(GeneralRemoteException("code: ${response.code()}"))
            }*/
        } catch (e: Exception) {
            println(e)
            Result.failure(e)
        }
    }
}
