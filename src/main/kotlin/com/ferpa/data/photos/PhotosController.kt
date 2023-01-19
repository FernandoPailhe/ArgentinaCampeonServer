package com.ferpa.data.photos

import com.ferpa.data.model.*
import com.ferpa.utils.Constants
import com.ferpa.utils.Constants.NEW_VERSUS_DEFAULT_VALUE
import com.ferpa.utils.Constants.NEW_VOTES_DEFAULT_VALUE
import com.ferpa.utils.Constants.RANDOM_RANGE_DEFAULT_VALUE

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

    suspend fun resetRank(newVotes: Int, newVersus: Int, randomRange: Int): Boolean {
        return dataSource.resetRank(newVotes, newVersus, randomRange)
    }

    suspend fun getBestPhotos(limit: Int = Constants.BEST_PHOTO_LIMIT, page: Int): List<Photo> {
        return dataSource.getBestPhotos(limit, page)
    }

    suspend fun getWorstPhotos(): List<Photo> {
        return dataSource.getWorstPhotos()
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

    suspend fun getPhotosByState(rarity: Int): List<Photo> {
        return dataSource.getPhotosByState(rarity)
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

    suspend fun massiveAdd(photoUrlList: List<String>): Boolean{
        return dataSource.massiveAdd(photoUrlList)
    }

    suspend fun postVote(vote: Vote): Boolean {
        return dataSource.postVote(vote)
    }

    suspend fun deleteOne(id: String): Boolean {
        return dataSource.deleteOneById(id)
    }

    suspend fun softUpdatePhoto(photo: Photo): Boolean {
        return dataSource.softUpdatePhoto(photo)
    }

    suspend fun fullUpdatePhoto(photo: Photo): Boolean {
        return dataSource.fullUpdatePhoto(photo)
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

    suspend fun updateState(photoId:String, rarity: Int): Boolean{
        return dataSource.updateState(photoId, rarity)
    }

}