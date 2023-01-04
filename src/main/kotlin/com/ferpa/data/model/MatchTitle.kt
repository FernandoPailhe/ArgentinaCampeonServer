package com.ferpa.data.model

import java.awt.DisplayMode
import java.util.*

@kotlinx.serialization.Serializable
data class MatchTitle(
    val id: String?,
    val title: String? = "",
    val score: String? = ""
)

