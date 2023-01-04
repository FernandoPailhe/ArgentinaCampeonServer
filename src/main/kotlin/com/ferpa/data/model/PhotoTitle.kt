package com.ferpa.data.model

import java.util.*

@kotlinx.serialization.Serializable
data class PhotoTitle(
    val id: String? = "",
    val insertDate: String?,
    val lastUpdate: String?,
    val votesUpdate: String?,
    val photoUrl: String?,
    val match: MatchTitle? = null,
    val players: List<PlayerTitle?>? = emptyList(),
    val photographer: PhotographerTitle? = null,
    val tags: List<String?>? = emptyList(),
    val rank: Double? = 0.0,
    val description: String? = "",
    val photoType: String? = "",
    val moment: MomentTitle? = null
)

