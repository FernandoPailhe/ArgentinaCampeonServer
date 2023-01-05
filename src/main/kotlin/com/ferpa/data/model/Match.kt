package com.ferpa.data.model

import java.time.LocalDateTime
import java.util.*

@kotlinx.serialization.Serializable
data class Match(
    val id: String?,
    val date: String? = "",
    val stadiumId: String? = "",
    val tournamentInstance: String? = "",
    val teamA: String? = null,
    val teamB: String? = null,
    val referee: String? = "",
    val score: String? = "",
    val scoreTeamA: List<String?>? = emptyList(),
    val scoreTeamB: List<String?>? = emptyList(),
    val lastUpdate: String? = "",
    val description: String? = "",
    val extra: String? = "",
)

fun Match.addUUID(): Match = this.copy(id = UUID.randomUUID().toString(), lastUpdate = LocalDateTime.now().toString() )

fun Match.updateMatch(): Match = this.copy(lastUpdate = LocalDateTime.now().toString())

fun Match.toMatchTitle(): MatchTitle {
    return MatchTitle(
        id = this.id,
        date = this.date,
        title = "${this.tournamentInstance}: ${this.teamA} vs. ${this.teamB}",
        score = this.score
    )
}
