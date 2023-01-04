package com.ferpa.data.players

import com.ferpa.data.model.Photo
import com.ferpa.data.model.Player

interface PlayerDataSource {

    suspend fun getAllPlayers(getFrom: String?): List<Player>

    suspend fun insertPlayer(player: Player)

    suspend fun getPlayerById(playerId: String): Player?

    suspend fun updatePlayer(player: Player): Boolean

    suspend fun deleteOneById(id: String): Boolean

}