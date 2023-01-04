package com.ferpa.data.model

@kotlinx.serialization.Serializable
data class MomentTitle(
    val id: String? = "",
    val date: String? = "",
    val matchTime: String? = "",
    val playType: String? = ""
)
