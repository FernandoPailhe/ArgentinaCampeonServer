package com.ferpa.data.photos

import com.ferpa.data.model.LastUpdatesResponse
import com.ferpa.data.model.Photo
import com.ferpa.data.model.Vote

interface PhotoDataSource {

    suspend fun getLastUpdatesDates(): LastUpdatesResponse

    suspend fun getAllPhotos(): List<Photo>

    suspend fun getPhotos(updateFrom: String? = ""): List<Photo>

    suspend fun getVersusPhotos(): List<Photo>

    suspend fun getPhotosByPlayer(playerId: String): List<Photo>

    suspend fun getPhotosByMatch(matchId: String): List<Photo>

    suspend fun getPhotosByTag(tag: String): List<Photo>

    suspend fun getPhotosByPhotographer(photographerId: String): List<Photo>

    suspend fun getPhotosByMoment(momentId: String): List<Photo>

    suspend fun getPhotoById(photoId: String): Photo?

    suspend fun deleteOneById(id: String): Boolean

    suspend fun insertPhoto(photo: Photo)

    suspend fun updatePhoto(photo: Photo): Boolean

    suspend fun postVote(vote: Vote): Boolean

}
