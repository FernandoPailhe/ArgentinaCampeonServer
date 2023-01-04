package com.ferpa.data.model

@kotlinx.serialization.Serializable
data class Stats(
    val minutes: Int? = null,
    val goals: Int? = null,
    val assists: Int? = null,
    val shots: Int? = null,
    val passes: Int? = null,
    val wrongPasses: Int? = null,
    val passAccuracy: Int? = null,
    val recoveries: Int? = null,
    val fouls: Int? = null,
    val foulsReceived: Int? = null,
    val yellowCards: Int? = null,
    val redCards: Int? = null
)

