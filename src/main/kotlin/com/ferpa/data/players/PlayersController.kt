package com.ferpa.data.players

import com.ferpa.data.model.Player

class PlayersController(
    private val dataSource: PlayerDataSource,
) {

    suspend fun getAll(getFrom: String? = ""): List<Player> {
        return dataSource.getAllPlayers(getFrom)
    }

    suspend fun insertPlayer(player: Player) {
        return dataSource.insertPlayer(player)
    }

    suspend fun getPlayerById(playerId: String): Player? {
        return dataSource.getPlayerById(playerId)
    }

    suspend fun updatePlayer(player: Player): Boolean {
        return dataSource.updatePlayer(player)
    }

    suspend fun deleteOne(id: String): Boolean {
        return dataSource.deleteOneById(id)
    }

}