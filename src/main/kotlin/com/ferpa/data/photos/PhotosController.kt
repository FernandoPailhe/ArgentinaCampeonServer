package com.ferpa.data.photos

import com.ferpa.data.model.*
import com.ferpa.utils.Constants

class PhotosController(
    private val dataSource: PhotoDataSource,
) {

    suspend fun getLastUpdatesDates(): LastUpdatesResponse {
        return dataSource.getLastUpdatesDates()
    }

    suspend fun getPhotos(getFrom: String?): List<Photo> {
        return dataSource.getPhotos(getFrom)
    }


    suspend fun getUpdatePhotos(getFrom: String?): List<Photo> {
        return dataSource.getUpdatePhotos(getFrom)
    }

    suspend fun getRankUpdates(getFrom: String?): List<RankUpdate> {
        return dataSource.getRankUpdates(getFrom)
    }

    suspend fun resetRank(newVotes: Long = 50, newVersus: Long = 70, randomRange: Int = 10): Boolean {
        return dataSource.resetRank(newVotes, newVersus, randomRange)
    }

    suspend fun getBestPhotos(limit: Int = Constants.BEST_PHOTO_LIMIT): List<Photo> {
        return dataSource.getBestPhotos(limit)
    }

    suspend fun getPhotosByPlayer(playerId: String): List<Photo> {
        return dataSource.getPhotosByPlayer(playerId)
    }

    suspend fun getPhotosByMatch(matchId: String): List<Photo> {
        return dataSource.getPhotosByMatch(matchId)
    }

    suspend fun getPhotosByTag(tag: String): List<Photo> {
        return dataSource.getPhotosByTag(tag)
    }

    suspend fun getPhotosByPhotographer(photographerId: String): List<Photo> {
        return dataSource.getPhotosByPhotographer(photographerId)
    }

    suspend fun getPhotosByMoment(momentId: String): List<Photo> {
        return dataSource.getPhotosByMoment(momentId)
    }

    suspend fun getPhotosByCustomQuery(customQuery: CustomQuery): List<Photo> {
        return dataSource.getPhotosByCustomQuery(customQuery)
    }

    suspend fun getPhotoById(photoId: String): Photo? {
        return dataSource.getPhotoById(photoId)
    }

    suspend fun insertPhoto(photo: Photo) {
        dataSource.insertPhoto(photo)
    }

    suspend fun postVote(vote: Vote): Boolean {
        return dataSource.postVote(vote)
    }

    suspend fun deleteOne(id: String): Boolean {
        return dataSource.deleteOneById(id)
    }

    suspend fun updatePhoto(photo: Photo): Boolean {
        return dataSource.updatePhotoAndKeepVotes(photo)
    }

    suspend fun updateAllMatchTitles(matchTitle: MatchTitle): Boolean {
        return dataSource.updateAllMatchTitles(matchTitle)
    }

    suspend fun updateAllPhotographerTitles(photographerTitle: PhotographerTitle): Boolean {
        return dataSource.updateAllPhotographerTitles(photographerTitle)
    }

    suspend fun updateAllPlayerTitles(playerTitle: PlayerTitle): Boolean {
        return dataSource.updateAllPlayerTitles(playerTitle)
    }

    suspend fun updateAllMomentTitles(momentTitle: MomentTitle): Boolean {
        return dataSource.updateAllMomentTitles(momentTitle)
    }

    suspend fun updateAllTags(tag: Tag): Boolean {
        return dataSource.updateAllTag(tag)
    }

}