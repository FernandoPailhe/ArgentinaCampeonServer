package com.ferpa.data.matches

import com.ferpa.data.model.Match
import com.ferpa.data.photos.PhotosController

interface MatchDataSource {

    suspend fun getAllMatches(getFrom: String?): List<Match>

    suspend fun insertMatch(match: Match)

    suspend fun getMatchById(matchId: String): Match?

    suspend fun updateMatch(match: Match, photosController: PhotosController): Boolean

    suspend fun deleteOneById(id: String): Boolean

}