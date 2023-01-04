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
    return PhotographerTitle(
        id = this.id,
        name = this.name
    )
}