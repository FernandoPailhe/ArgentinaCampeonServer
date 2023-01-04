package com.ferpa.data.players

import com.ferpa.data.model.*
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq
import org.litote.kmongo.gt

class PlayerDataSourceImpl(
    private val db: CoroutineDatabase,
) : PlayerDataSource {

    private val collection = db.getCollection<Player>()

    override suspend fun getAllPlayers(getFrom: String?): List<Player> {
        return if (getFrom.isNullOrEmpty()) {
            collection.find()
                .descendingSort(Player::lastUpdate)
                .toList()
        } else {
            collection.find(Player::lastUpdate gt getFrom)
                .descendingSort(Player::lastUpdate)
                .toList()
        }
    }

    override suspend fun insertPlayer(player: Player) {
        collection.insertOne(player.addUUID())
    }

    override suspend fun getPlayerById(playerId: String): Player? {
        return collection.findOne(Player::id eq playerId)
    }

    override suspend fun updatePlayer(player: Player): Boolean {
        return try {
            collection.updateOne(Player::id eq player.id, player.updatePlayer()).wasAcknowledged()
            true
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun deleteOneById(id: String): Boolean {
        return collection.deleteOne(Player::id eq id).wasAcknowledged()
    }

}