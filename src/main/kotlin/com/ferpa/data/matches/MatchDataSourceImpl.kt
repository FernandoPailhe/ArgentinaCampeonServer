package com.ferpa.data.matches

import com.ferpa.data.model.*
import com.ferpa.data.photos.PhotosController
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq
import org.litote.kmongo.gt

class MatchDataSourceImpl(
    private val db: CoroutineDatabase,
) : MatchDataSource {

    private val collection = db.getCollection<Match>()

    override suspend fun getAllMatches(getFrom: String?): List<Match> {
        return if(getFrom.isNullOrEmpty()) {
            collection.find()
                .ascendingSort(Match::date)
                .toList()
        } else {
            collection.find(Match::lastUpdate gt getFrom)
                .descendingSort(Match::lastUpdate)
                .toList()
        }
    }

    override suspend fun insertMatch(match: Match) {
        collection.insertOne(match.addUUID())
    }

    override suspend fun getMatchById(matchId: String): Match? {
        return collection.findOne(Match::id eq matchId)
    }

    override suspend fun updateMatch(match: Match, photosController: PhotosController): Boolean {
        return try {
            if (haveToUpdate(match)) photosController.updateAllMatchTitles(match.toMatchTitle())
            collection.updateOne(Match::id eq match.id, match.updateMatch()).wasAcknowledged()
            true
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun deleteOneById(id: String): Boolean {
        return collection.deleteOne(Match::id eq id).wasAcknowledged()
    }

    private suspend fun haveToUpdate(match: Match): Boolean {
        val oldMatch = match.id?.let { getMatchById(it) }
        return (oldMatch?.toMatchTitle() != match.toMatchTitle())
    }
}