package com.ferpa.data.matches

import com.ferpa.data.model.Match
import com.ferpa.data.photos.PhotosController

class MatchesController(
    private val dataSource: MatchDataSource,
) {

    suspend fun getAll(getFrom: String? = ""): List<Match> {
        return dataSource.getAllMatches(getFrom)
    }

    suspend fun insertMatch(match: Match) {
        return dataSource.insertMatch(match)
    }

    suspend fun getMatchById(matchId: String): Match? {
        return dataSource.getMatchById(matchId)
    }

    suspend fun updateMatch(match: Match, photosController: PhotosController): Boolean {
        return dataSource.updateMatch(match, photosController)
    }

    suspend fun deleteOne(id: String): Boolean {
        return dataSource.deleteOneById(id)
    }

}