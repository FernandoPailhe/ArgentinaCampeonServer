package com.ferpa.data.model

@kotlinx.serialization.Serializable
data class Tag(
    val id: String,
    val tag: String?,
    val relativesTagsIds: List<String?>?
)
