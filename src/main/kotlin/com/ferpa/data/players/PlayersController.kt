package com.ferpa.data.players

import com.ferpa.data.model.Player
import com.ferpa.data.photos.PhotosController

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
        return dataSource.getOneById(playerId)
    }

    suspend fun updatePlayer(player: Player, photosController: PhotosController): Boolean {
        return dataSource.updatePlayer(player, photosController)
    }

    suspend fun deleteOne(id: String): Boolean {
        return dataSource.deleteOneById(id)
    }

}