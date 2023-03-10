package com.ferpa.data.model

import java.time.LocalDateTime
import java.util.*

@kotlinx.serialization.Serializable
data class Moment(
    val id: String? = "",
    val date: String? = "",
    val gameTime: Int?,
    val additionalTime: Int?,
    val description: String? = "",
    val playType: String? = "",
    val lastUpdate: String? = ""
)

fun Moment.addUUID(): Moment = this.copy(id = UUID.randomUUID().toString(), lastUpdate = LocalDateTime.now().toString())

fun Moment.updateMoment(): Moment = this.copy(lastUpdate = LocalDateTime.now().toString())

fun Moment.toMomentTitle(): MomentTitle {
    return MomentTitle(
        this.id,
        this.date,
        this.gameTime,
        this.additionalTime,
        this.playType
    )
}