package com.ferpa.data.model

import java.util.*

@kotlinx.serialization.Serializable
data class NationalTeam(
    val id: String?,
    val name: String? = "",
    val nickName: String? = "",
    val logoUrl: String? = "",
    val flagUrl: String? = ""
)
