package com.ferpa.data.photos

import com.ferpa.data.model.*
import com.ferpa.utils.toRankUpdateList
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

    override suspend fun getPhotos(getFrom: String?): List<Photo> {
        return if (getFrom.isNullOrEmpty()) {
            collection.find().descendingSort(Photo::insertDate).toList()
        } else {
            collection.find(Photo::insertDate gt getFrom)
                .descendingSort(Photo::insertDate)
                .toList()
        }
    }

    override suspend fun getUpdatePhotos(getFrom: String?): List<Photo> {
        return if (getFrom.isNullOrEmpty()) {
            collection.find().descendingSort(Photo::lastUpdate).toList()
        } else {
            collection.find(Photo::lastUpdate gt getFrom)
                .descendingSort(Photo::lastUpdate)
                .toList()
        }
    }

    override suspend fun getRankUpdates(getFrom: String?): List<RankUpdate> {
        return if (getFrom.isNullOrEmpty()) {
            collection.find().descendingSort(Photo::votesUpdate).toList().toRankUpdateList()
        } else {
            collection.find(Photo::votesUpdate gt getFrom)
                .descendingSort(Photo::votesUpdate)
                .toList()
                .toRankUpdateList()
        }
    }

    override suspend fun getBestPhotos(limit: Int): List<Photo> {
        return collection.find().descendingSort(Photo::rank).limit(limit).toList()
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

    override suspend fun getPhotosByTag(tagId: String): List<Photo> {
        return collection.find(Photo::tags / Tag::id eq tagId)
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
            win?.apply {
                collection.updateOne(Photo::id eq vote.voteWin, win.voteWin(vote.superVote)).wasAcknowledged()
            }
            lost?.apply { collection.updateOne(Photo::id eq vote.voteLost, lost.voteLost()).wasAcknowledged() }
            true
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun updateAllMatchTitles(matchTitle: MatchTitle): Boolean {
        return try {
            if (matchTitle.id != null) {
                getPhotosByMatch(matchTitle.id).forEach { oldPhoto ->
                    if (oldPhoto.match != matchTitle) {
                        val newPhoto = oldPhoto.copy(match = matchTitle)
                        updatePhoto(newPhoto.updatePhoto(oldPhoto))
                    } else {
                        return true
                    }
                }
                true
            } else false
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun updateAllPhotographerTitles(newTitle: PhotographerTitle): Boolean {
        return try {
            if (newTitle.id != null) {
                getPhotosByPhotographer(newTitle.id).forEach { oldPhoto ->
                    if (oldPhoto.photographer != newTitle) {
                        val newPhoto = oldPhoto.copy(photographer = newTitle)
                        updatePhoto(newPhoto.updatePhoto(oldPhoto))
                    } else {
                        return true
                    }
                }
                true
            } else false
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun updateAllPlayerTitles(newTitle: PlayerTitle): Boolean {
        return try {
            if (newTitle.id != null) {
                getPhotosByPlayer(newTitle.id).forEach { oldPhoto ->
                    val newPlayerTitleList = mutableListOf<PlayerTitle?>()
                    oldPhoto.players?.forEach { oldPlayerTitle ->
                        if (oldPlayerTitle?.id == newTitle.id) {
                            newPlayerTitleList.add(newTitle)
                        } else {
                            newPlayerTitleList.add(oldPlayerTitle)
                        }
                    }
                    val newPhoto = oldPhoto.copy(players = newPlayerTitleList)
                    updatePhoto(newPhoto.updatePhoto(oldPhoto))
                }
                true
            } else false
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun updateAllMomentTitles(newTitle: MomentTitle): Boolean {
        return try {
            if (newTitle.id != null) {
                getPhotosByMoment(newTitle.id).forEach { oldPhoto ->
                    if (oldPhoto.moment != newTitle) {
                        val newPhoto = oldPhoto.copy(moment = newTitle)
                        updatePhoto(newPhoto.updatePhoto(oldPhoto))
                    } else {
                        return true
                    }
                }
                true
            } else false
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun updateAllTag(tag: Tag): Boolean {
        return try {
            getPhotosByTag(tag.id).forEach { oldPhoto ->
                val newTagList = mutableListOf<Tag?>()
                oldPhoto.tags?.forEach { oldTag ->
                    if (oldTag?.id == tag.id) {
                        newTagList.add(tag)
                    } else {
                        newTagList.add(oldTag)
                    }
                }
                val newPhoto = oldPhoto.copy(tags = newTagList)
                updatePhoto(newPhoto.updatePhoto(oldPhoto))
            }
            true
        } catch (e: Exception) {
            false
        }
    }

}