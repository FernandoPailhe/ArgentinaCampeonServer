package com.ferpa.data.players

import com.ferpa.data.model.Player
import com.ferpa.data.photos.PhotosController

interface PlayerDataSource {

    suspend fun getAllPlayers(getFrom: String?): List<Player>

    suspend fun insertPlayer(player: Player)

    suspend fun getOneById(playerId: String): Player?

    suspend fun updatePlayer(player: Player, photosController: PhotosController): Boolean

    suspend fun deleteOneById(id: String): Boolean

}