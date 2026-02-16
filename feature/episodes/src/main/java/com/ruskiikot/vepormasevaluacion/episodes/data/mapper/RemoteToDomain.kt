package com.ruskiikot.vepormasevaluacion.episodes.data.mapper

import com.ruskiikot.vepormasevaluacion.episodes.domain.model.Episode
import com.ruskiikot.vepormasevaluacion.episodes.domain.model.ShowSummary
import com.ruskiikot.vepormasevaluacion.network.EpisodeResponse

fun List<EpisodeResponse>.toDomain(): List<Episode> {
    return this.map {
        Episode(
            id = it.id,
            airDate = it.airdate,
            airTime = it.airtime,
            show = ShowSummary(
                id = it.show.id,
                name = it.show.name,
                imageMediumUrl = it.show.image?.medium
                    ?: "https://static.tvmaze.com/images/no-img/no-img-portrait-text.png",
                networkName = it.show.network?.name ?: "",
            )
        )
    }
}
