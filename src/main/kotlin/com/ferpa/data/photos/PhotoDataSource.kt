package com.ferpa.data.photos

import com.ferpa.data.model.*

interface PhotoDataSource {

    suspend fun getLastUpdatesDates(): LastUpdatesResponse

    suspend fun getPhotos(getFrom: String? = ""): List<Photo>

    suspend fun getUpdatePhotos(getFrom: String?): List<Photo>

    suspend fun getRankUpdates(getFrom: String?): List<RankUpdate>

    suspend fun getBestPhotos(limit: Int): List<Photo>

    suspend fun getPhotosByPlayer(playerId: String): List<Photo>

    suspend fun getPhotosByMatch(matchId: String): List<Photo>

    suspend fun getPhotosByTag(tagId: String): List<Photo>

    suspend fun getPhotosByPhotographer(photographerId: String): List<Photo>

    suspend fun getPhotosByMoment(momentId: String): List<Photo>

    suspend fun getPhotosByCustomQuery(customQuery: CustomQuery): List<Photo>

    suspend fun getPhotoById(photoId: String): Photo?

    suspend fun deleteOneById(id: String): Boolean

    suspend fun insertPhoto(photo: Photo)

    suspend fun updatePhoto(photo: Photo): Boolean

    suspend fun postVote(vote: Vote): Boolean

    suspend fun updateAllMatchTitles(matchTitle: MatchTitle): Boolean

    suspend fun updateAllPhotographerTitles(newTitle: PhotographerTitle): Boolean

    suspend fun updateAllPlayerTitles(newTitle: PlayerTitle): Boolean

    suspend fun updateAllMomentTitles(newTitle: MomentTitle): Boolean

    suspend fun updateAllTag(tag: Tag): Boolean

}
