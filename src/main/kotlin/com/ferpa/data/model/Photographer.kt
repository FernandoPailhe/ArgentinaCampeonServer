package com.ferpa.data.model

import java.time.LocalDateTime
import java.util.*

@kotlinx.serialization.Serializable
data class Photographer(
    val id: String? = null,
    val name: String? = null,
    val country: String? = null,
    val web: String? = null,
    val source: String? = null,
    val links: List<String?>? = null,
    val lastUpdate: String? = null
)

fun Photographer.addUUID(): Photographer = this.copy(id = UUID.randomUUID().toString(), lastUpdate = LocalDateTime.now().toString())

fun Photographer.updatePhotographer(): Photographer = this.copy(lastUpdate = LocalDateTime.now().toString())

fun Photographer.toPhotographerTitle(): PhotographerTitle {
    val photographerDisplayName = if (!this.name.isNullOrEmpty() && !this.source.isNullOrEmpty()) {
        "${this.name} - ${this.source}"
    } else if (!this.name.isNullOrEmpty()) {
        this.name
    }  else if (!this.source.isNullOrEmpty()) {
        this.source
    } else {
        "Fuente desconocida"
    }
    return PhotographerTitle(
        id = this.id,
        name = photographerDisplayName
    )
}