package com.ferpa.data.model

@kotlinx.serialization.Serializable
data class LastUpdatesResponse(
    val lastInsertDate: String?,
    val lastUpdate: String?,
    val lastVotesUpdate: String?
)
