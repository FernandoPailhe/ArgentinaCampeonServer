package com.ferpa.data.model

import java.time.LocalDateTime
import java.util.*

@kotlinx.serialization.Serializable
data class AppInfo(
    val id: String? = "",
    val welcome: List<Info?>? = emptyList(),
    val tutorial: List<Info?>? = emptyList(),
    val share: List<Info?>? = emptyList(),
    val aboutUs: List<Info?>? = emptyList(),
    val extra: List<Info?>? = emptyList(),
    val lastUpdate: String? = ""
)

fun AppInfo.new(): AppInfo = this.copy(id = UUID.randomUUID().toString(), lastUpdate = LocalDateTime.now().toString())

fun AppInfo.update(): AppInfo = this.copy(lastUpdate = LocalDateTime.now().toString())