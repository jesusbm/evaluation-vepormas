package com.ruskiikot.vepormasevaluacion.episodes.domain.model

import com.ruskiikot.vepormasevaluacion.episodes.domain.model.ShowSummary

data class Episode(
    val id: Long,
    val airDate: String,
    val airTime: String,
    val show: ShowSummary,
)