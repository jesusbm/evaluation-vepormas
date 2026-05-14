package com.ruskiikot.vepormasevaluacion.showdetails.data.datasource

import com.ruskiikot.vepormasevaluacion.showdetails.domain.model.ShowDetails

interface ShowDetailsRemoteDataSource {

    suspend fun getShowDetails(showId: Long): Result<ShowDetails>
}
