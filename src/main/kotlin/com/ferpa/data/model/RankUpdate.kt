package com.ferpa.data.model

@kotlinx.serialization.Serializable
data class RankUpdate(
    val photoId: String? = "",
    val rank: Double? = 0.0,
)
