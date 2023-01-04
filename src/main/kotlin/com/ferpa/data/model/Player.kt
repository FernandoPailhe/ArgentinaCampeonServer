package com.ferpa.data.model

import java.time.LocalDateTime
import java.util.*

@kotlinx.serialization.Serializable
data class Player(
    val id: String?,
    val displayName: String? = "",
    val name: String = "",
    val nickName: String? = "",
    val birthday: Int? = null,
    val position: String? = null,
    val nationalTeam: String? = "",
    val number: Int? = null,
    val team: String? = "",
    val profilePhotoUrl: String? = "",
    val lastUpdate: String? = "",
    val stats: Stats? = null
)

fun Player.addUUID(): Player = this.copy(id = UUID.randomUUID().toString(), lastUpdate = LocalDateTime.now().toString())

fun Player.updatePlayer(): Player = this.copy(lastUpdate = LocalDateTime.now().toString())

fun Player.toPlayerTitle(): PlayerTitle {
    val displayName = if (this.displayName.isNullOrEmpty()) {
        if (this.nickName != null) {
            "${this.nickName} ${this.name.split(" ").last()}"
        } else {
            name
        }
    } else this.displayName

    return PlayerTitle(
        id = this.id,
        displayName = displayName,
        nationalTeam = this.nationalTeam
    )
}