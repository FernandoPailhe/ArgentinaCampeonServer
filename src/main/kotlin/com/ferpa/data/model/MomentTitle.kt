package com.ferpa.data.model

@kotlinx.serialization.Serializable
data class MomentTitle(
    val id: String? = "",
    val date: String? = "",
    val gameTime: Int?,
    val additionalTime: Int?,
    val playType: String? = ""
)
