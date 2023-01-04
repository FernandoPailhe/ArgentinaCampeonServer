package com.ferpa.data.model

import java.time.LocalDateTime
import java.util.*

@kotlinx.serialization.Serializable
data class Tag(
    val id: String,
    val tag: String?,
    val lastUpdate: String?
)

fun Tag.new(): Tag = this.copy(id = UUID.randomUUID().toString(), lastUpdate = LocalDateTime.now().toString())

fun Tag.update(): Tag = this.copy(lastUpdate = LocalDateTime.now().toString())