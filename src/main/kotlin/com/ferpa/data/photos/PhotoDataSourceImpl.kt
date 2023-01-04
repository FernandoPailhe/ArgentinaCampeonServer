package com.ferpa.data.photos

import com.ferpa.data.model.*
import com.ferpa.utils.Constants.VOTE_ROLL
import org.litote.kmongo.*
import org.litote.kmongo.coroutine.CoroutineDatabase

class PhotoDataSourceImpl(
    private val db: CoroutineDatabase,
) : PhotoDataSource {

    //Create the collection
    private val collection = db.getCollection<Photo>()

    override suspend fun getLastUpdatesDates(): LastUpdatesResponse {
        val lastInsertDate = collection.find().descendingSort(Photo::insertDate).first()?.insertDate ?: ""
        val lastUpdate = collection.find().descendingSort(Photo::lastUpdate).first()?.lastUpdate ?: ""
        val lastVotesUpdate = collection.find().descendingSort(Photo::votesUpdate).first()?.votesUpdate ?: ""
        return LastUpdatesResponse(
            lastInsertDate = lastInsertDate,
            lastUpdate = lastUpdate,
            lastVotesUpdate = lastVotesUpdate
        )
    }

    override suspend fun getAllPhotos(): List<Photo> {
        return collection.find()
            .descendingSort(Photo::rank)
            .toList()
    }

    override suspend fun getPhotos(updateFrom: String?): List<Photo> {
        return collection.find(Photo::insertDate gt updateFrom).toList()
    }

    override suspend fun getVersusPhotos(): List<Photo> {
        return collection.find()
            .ascendingSort(Photo::votes).limit(VOTE_ROLL)
            .toList()
    }

    override suspend fun getPhotosByPlayer(playerId: String): List<Photo> {
        return collection.find(Photo::players / PlayerTitle::id eq playerId)
            .descendingSort(Photo::rank)
            .toList()
    }

    override suspend fun getPhotosByMatch(matchId: String): List<Photo> {
        return collection.find(Photo::match / MatchTitle::id eq matchId)
            .descendingSort(Photo::rank)
            .toList()
    }

    override suspend fun getPhotosByTag(tag: String): List<Photo> {
        return collection.find(Photo::tags contains tag)
            .descendingSort(Photo::rank)
            .toList()
    }

    override suspend fun getPhotosByPhotographer(photographerId: String): List<Photo> {
        return collection.find(Photo::photographer / PhotographerTitle::id eq photographerId)
            .descendingSort(Photo::rank)
            .toList()
    }

    override suspend fun getPhotosByMoment(momentId: String): List<Photo> {
        return collection.find(Photo::moment / MomentTitle::id eq momentId)
            .descendingSort(Photo::rank)
            .toList()
    }

    override suspend fun getPhotoById(photoId: String): Photo? {
        return collection.findOne(Photo::id eq photoId)
    }

    override suspend fun deleteOneById(id: String): Boolean {
        return collection.deleteOne(Photo::id eq id).wasAcknowledged()
    }

    override suspend fun insertPhoto(photo: Photo) {
        collection.insertOne(photo.newPhoto())
    }

    override suspend fun updatePhoto(photo: Photo): Boolean {
        return try {
            val oldPhoto = collection.findOne(Photo::id eq photo.id)
            if (oldPhoto != null) {
                collection.updateOne(Photo::id eq photo.id, photo.updatePhoto(oldPhoto)).wasAcknowledged()
                true
            } else {
                false
            }
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun postVote(vote: Vote): Boolean {
        return try {
            val win = collection.findOne(Photo::id eq vote.voteWin)
            val lost = collection.findOne(Photo::id eq vote.voteLost)
            win?.apply { collection.updateOne(Photo::id eq vote.voteWin, win.voteWin(vote.superVote)).wasAcknowledged() }
            lost?.apply { collection.updateOne(Photo::id eq vote.voteLost, lost.voteLost()).wasAcknowledged() }
            true
        } catch (e: Exception) {
            false
        }
    }

}