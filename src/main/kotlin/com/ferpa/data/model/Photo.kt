package com.ferpa.data.model

import com.ferpa.utils.Constants.SUPER_VOTE_AMOUNT
import com.ferpa.utils.divideToPercent
import java.time.LocalDateTime

import java.util.*

@kotlinx.serialization.Serializable
data class Photo(
    val id: String? = "",
    val insertDate: String? = LocalDateTime.now().toString(),
    val lastUpdate: String? = LocalDateTime.now().toString(),
    val votesUpdate: String = "",
    val date: String? = "",
    val photoUrl: String?,
    val match: MatchTitle? = null,
    val players: List<PlayerTitle?>? = emptyList(),
    val photographer: PhotographerTitle? = null,
    val tags: List<Tag?>? = emptyList(),
    val votes: Long = 18,
    val versus: Long = 20,
    val rank: Double? = 0.66,
    val extra: String? = "",
    val description: String? = "",
    val photoType: String? = "",
    val moment: MomentTitle? = null,
    val rarity: Int? = 0
)

fun Photo.newPhoto(): Photo = this.copy(
    id = UUID.randomUUID().toString(),
    insertDate = LocalDateTime.now().toString(),
    lastUpdate = LocalDateTime.now().toString(),
    votesUpdate = LocalDateTime.now().toString()
)

fun Photo.updatePhoto(oldPhoto: Photo): Photo = this.copy(lastUpdate = LocalDateTime.now().toString(), votes = oldPhoto.votes, versus = oldPhoto.versus, rank = oldPhoto.rank, votesUpdate = oldPhoto.votesUpdate)

fun Photo.voteWin(superVote: Boolean = false): Photo {
    val votes = if (superVote) this.votes + SUPER_VOTE_AMOUNT else this.votes.inc()
    val versus = if (superVote) this.versus + SUPER_VOTE_AMOUNT else this.versus.inc()
    val rank = votes.divideToPercent(versus)
    return this.copy(votes = votes, versus = versus, rank = rank, votesUpdate = LocalDateTime.now().toString())
}

fun Photo.voteLost(): Photo {
    val versus = this.versus.inc()
    val rank = votes.divideToPercent(this.versus)
    return this.copy(versus = versus, rank = rank, votesUpdate = LocalDateTime.now().toString())
}

fun Photo.toRankUpdate(): RankUpdate = RankUpdate(this.id, this.rank)
