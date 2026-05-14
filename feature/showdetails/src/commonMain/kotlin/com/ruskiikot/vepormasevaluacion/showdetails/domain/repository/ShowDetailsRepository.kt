package com.ruskiikot.vepormasevaluacion.showdetails.domain.repository

import com.ruskiikot.vepormasevaluacion.showdetails.domain.model.ShowDetails

interface ShowDetailsRepository {

    suspend fun getShowDetails(showId: Long): ShowDetails
}