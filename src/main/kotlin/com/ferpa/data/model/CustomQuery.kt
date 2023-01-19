package com.ferpa.data.model

import org.litote.kmongo.*
import org.litote.kmongo.coroutine.CoroutineCollection

data class CustomQuery(
    val playerId: String? = "",
    val matchId: String? = "",
    val tagId: String? = "",
    val photographerId: String? = "",
    val momentId: String? = "",
    val sortBy: String? = "",
    val sortAscending: Boolean = true,
)

suspend fun CustomQuery.filter(collection: CoroutineCollection<Photo>): List<Photo>? {

    val filterPlayer = collection.find()
        .takeUnless { this.playerId.isNullOrEmpty() }
        .let { it?.filter(Photo::players / PlayerTitle::id eq this.playerId) }
        ?.toList()?.filter { it.rarity >= PhotoState.HideState.rarity }

    val filterMatch = collection.find()
        .takeUnless { this.matchId.isNullOrEmpty() }
        .let { it?.filter(Photo::match / MatchTitle::id eq this.matchId) }
        ?.toList()?.filter { it.rarity >= PhotoState.HideState.rarity }

    val filterTag = collection.find()
        .takeUnless { this.tagId.isNullOrEmpty() }
        .let { it?.filter(Photo::tags / Tag::id eq this.tagId) }
        ?.toList()?.filter { it.rarity >= PhotoState.HideState.rarity }

    val filterPhotographer = collection.find()
        .takeUnless { this.photographerId.isNullOrEmpty() }
        .let { it?.filter(Photo::photographer / PhotographerTitle::id eq this.photographerId) }
        ?.toList()?.filter { it.rarity >= PhotoState.HideState.rarity }

    val filterMoment = collection.find()
        .takeUnless { this.momentId.isNullOrEmpty() }
        .let { it?.filter(Photo::moment / MomentTitle::id eq this.momentId) }
        ?.toList()?.filter { it.rarity >= PhotoState.HideState.rarity }

    val filteredLists = listOf(
        filterPlayer, filterMatch, filterTag, filterPhotographer, filterMoment
    ).filterNot { it == null }

    return if (filteredLists.size == 1) {
        filteredLists[0]
    } else if (filteredLists.isNullOrEmpty()) {
        emptyList()
    } else {
        var filterList = mutableSetOf<Photo>()

        for ((index, value) in filteredLists.withIndex()) {
            if (index == 0) {
                filteredLists[1]?.let { value?.intersect(it.toSet())?.toMutableSet() }?.let {
                    filterList = it
                }
            } else if (index + 1 < filteredLists.size) {
                filteredLists[index + 1]?.let { filterList.intersect(it.toSet()).toMutableSet() }?.let {
                    filterList = it
                }
            }

        }
        filterList.toList()
    }

}