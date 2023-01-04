package com.ferpa.data.model

@kotlinx.serialization.Serializable
data class PlayerTitle(
    val id: String?,
    val displayName: String? = "",
    val nationalTeam: String? = ""
)
