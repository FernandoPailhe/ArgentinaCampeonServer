package com.ferpa.data.photos

import com.ferpa.data.model.*

class PhotosController(
    private val dataSource: PhotoDataSource,
) {

    suspend fun getLastUpdatesDates(): LastUpdatesResponse {
        return dataSource.getLastUpdatesDates()
    }

    suspend fun getAllPhotos(): List<Photo> {
        return dataSource.getAllPhotos()
    }

    suspend fun getPhotos(updateFrom: String?): List<Photo> {
        return dataSource.getPhotos(updateFrom)
    }

    suspend fun getVersusPhotos(): List<Photo> {
        return dataSource.getVersusPhotos()
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
        return dataSource.updatePhoto(photo)
    }

    suspend fun updateAllMatchTitles(matchTitle: MatchTitle): Boolean {
        return dataSource.updateAllMatchTitles(matchTitle)
    }

    suspend fun updateAllPhotographerTitles(photographerTitle: PhotographerTitle): Boolean{
        return dataSource.updateAllPhotographerTitles(photographerTitle)
    }

    suspend fun updateAllPlayerTitles(playerTitle: PlayerTitle): Boolean{
        return dataSource.updateAllPlayerTitles(playerTitle)
    }

    suspend fun updateAllMomentTitles(momentTitle: MomentTitle): Boolean{
        return dataSource.updateAllMomentTitles(momentTitle)
    }

    suspend fun updateAllTags(tag: Tag): Boolean{
        return dataSource.updateAllTag(tag)
    }

}