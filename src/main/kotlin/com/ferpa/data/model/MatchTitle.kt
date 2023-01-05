package com.ferpa.data.model

@kotlinx.serialization.Serializable
data class MatchTitle(
    val id: String?,
    val date: String? = "",
    val title: String? = "",
    val score: String? = ""
)

