package com.ruskiikot.vepormasevaluacion.showdetails.data.mapper

import com.ruskiikot.vepormasevaluacion.network.ShowResponse
import com.ruskiikot.vepormasevaluacion.showdetails.domain.model.ShowDetails

fun ShowResponse.toDomain(): ShowDetails {
    return ShowDetails(
        id = this.id,
        name = this.name,
        imageMediumUrl = this.image?.medium,
        networkName = this.network.name,
        averageRating = this.rating.average ?: "",
        summary = this.summary
            .replace("<p>", "")
            .replace("</p>", " ")
            .replace("<b>", " ")
            .replace("</b>", " ")
            .replace("<i>", "")
            .replace("</i>", ""),
        genres = this.genres,
        scheduleTime = this.schedule.time,
        scheduleDays = this.schedule.days,
    )
}
