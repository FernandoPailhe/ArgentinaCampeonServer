package com.ferpa.data.model

@kotlinx.serialization.Serializable
data class Vote(
    val voteWin: String? = "",
    val voteLost: String? = "",
    val superVote: Boolean = false,
)
