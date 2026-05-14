package com.ruskiikot.vepormasevaluacion.showdetails.domain.model

data class ShowDetails(
    val id: Long,
    val name: String,
    val imageMediumUrl: String?,
    val networkName: String,
    val averageRating: String,
    val summary: String,
    val genres: List<String>,
    val scheduleTime: String,
    val scheduleDays: List<String>,
)