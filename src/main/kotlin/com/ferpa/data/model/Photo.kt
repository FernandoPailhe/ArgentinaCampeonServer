package com.ferpa.data.model

import com.ferpa.utils.Constants.NEW_VERSUS_DEFAULT_VALUE
import com.ferpa.utils.Constants.NEW_VOTES_DEFAULT_VALUE
import com.ferpa.utils.Constants.RANDOM_RANGE_DEFAULT_VALUE
import com.ferpa.utils.Constants.SUPER_VOTE_AMOUNT
import com.ferpa.utils.divideToPercent
import java.time.LocalDateTime
import kotlin.random.Random
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
    val votes: Long = NEW_VOTES_DEFAULT_VALUE.toLong(),
    val versus: Long = NEW_VERSUS_DEFAULT_VALUE.toLong(),
    val rank: Double? = 0.66,
    val extra: String? = "",
    val description: String? = "",
    val photoType: String? = "",
    val moment: MomentTitle? = null,
    val rarity: Int = 0,
)

fun Photo.newPhoto(): Photo {
    val votes = NEW_VOTES_DEFAULT_VALUE + Random.nextInt(0, RANDOM_RANGE_DEFAULT_VALUE).toLong()
    val versus = NEW_VERSUS_DEFAULT_VALUE + Random.nextInt(0, RANDOM_RANGE_DEFAULT_VALUE).toLong()
    return this.copy(
        id = UUID.randomUUID().toString(),
        insertDate = LocalDateTime.now().toString(),
        lastUpdate = LocalDateTime.now().toString(),
        votesUpdate = LocalDateTime.now().toString(),
        votes = votes,
        versus = versus,
        rank = votes.divideToPercent(versus)
    )
}

fun Photo.softUpdatePhoto(oldPhoto: Photo): Photo = this.copy(lastUpdate = LocalDateTime.now().toString(),
    votes = oldPhoto.votes,
    versus = oldPhoto.versus,
    rank = oldPhoto.rank,
    votesUpdate = oldPhoto.votesUpdate)

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

fun Photo.resetRank(
    newVotes: Int = NEW_VOTES_DEFAULT_VALUE,
    newVersus: Int = NEW_VERSUS_DEFAULT_VALUE,
    randomRange: Int = RANDOM_RANGE_DEFAULT_VALUE,
): Photo {
    val votes = newVotes + Random.nextInt(0, randomRange).toLong()
    val versus = newVersus + Random.nextInt(0, randomRange).toLong()
    return this.copy(
        lastUpdate = LocalDateTime.now().toString(),
        votesUpdate = LocalDateTime.now().toString(),
        votes = votes,
        versus = versus,
        rank = votes.divideToPercent(versus)
    )
}

fun Photo.fullUpdate(): Photo {
    return this.copy(
        insertDate = LocalDateTime.now().toString(),
        lastUpdate = LocalDateTime.now().toString(),
        votesUpdate = LocalDateTime.now().toString(),
        rank = this.votes.divideToPercent(this.versus)
    )
}

fun Photo.updateState(rarity: Int): Photo {
    return this.copy(
        insertDate = LocalDateTime.now().toString(),
        lastUpdate = LocalDateTime.now().toString(),
        votesUpdate = LocalDateTime.now().toString(),
        rarity = rarity
    )
}
