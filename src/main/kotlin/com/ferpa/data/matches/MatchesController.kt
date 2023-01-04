package com.ferpa.data.matches

import com.ferpa.data.model.Match

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

    suspend fun updateMatch(match: Match): Boolean {
        return dataSource.updateMatch(match)
    }

    suspend fun deleteOne(id: String): Boolean {
        return dataSource.deleteOneById(id)
    }

}