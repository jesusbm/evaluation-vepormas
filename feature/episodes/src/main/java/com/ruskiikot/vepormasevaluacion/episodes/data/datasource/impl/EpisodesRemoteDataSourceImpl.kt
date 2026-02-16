package com.ruskiikot.vepormasevaluacion.episodes.data.datasource.impl

import com.ruskiikot.vepormasevaluacion.domain.exception.EmptyContentRemoteException
import com.ruskiikot.vepormasevaluacion.domain.exception.GeneralRemoteException
import com.ruskiikot.vepormasevaluacion.episodes.domain.model.Episode
import com.ruskiikot.vepormasevaluacion.episodes.data.datasource.EpisodesRemoteDataSource
import com.ruskiikot.vepormasevaluacion.episodes.data.mapper.toDomain
import com.ruskiikot.vepormasevaluacion.network.TvMazeApiClient
import javax.inject.Inject

class EpisodesRemoteDataSourceImpl @Inject constructor(
    private val tvMazeApiClient: TvMazeApiClient,
) : EpisodesRemoteDataSource {

    override suspend fun getEpisodes(countryCode: String, date: String): Result<List<Episode>> {
        return try {
            val response = tvMazeApiClient.getSchedule(countryCode, date)
            val content = response.body()
            when {
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
