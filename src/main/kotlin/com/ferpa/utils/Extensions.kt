package com.ferpa.utils

import com.ferpa.data.matches.MatchesController
import com.ferpa.data.model.*
import com.ferpa.data.photographer.PhotographersController
import com.ferpa.data.players.PlayersController
import kotlin.math.roundToInt

fun Long.divideToPercent(divideTo: Long) = ((this.toDouble() / divideTo) * 100).roundToInt() / 100.0

suspend fun List<Photo>.toListOfPhotoTitle(
    playersController: PlayersController,
    matchesController: MatchesController,
    photographersController: PhotographersController,
): List<PhotoTitle> {
    return this.map { photo ->
        /*
        val playersTitle = photo.players?.filterNotNull()?.map { playerId ->
            playersController.getPlayerById(playerId)?.toPlayerTitle()
        }
        val matchTitle = if (photo.matchTitle.isNullOrEmpty()) {
            null
        } else {
            matchesController.getMatchById(photo.matchTitle)?.toMatchTitle()
        }
        val photographerTitle = if (photo.photographerTitle.isNullOrEmpty()) {
            null
        } else {
            photographersController.getPhotographerById(photo.photographerTitle)?.toPhotographerTitle()
        }

         */
        photo.toPhotoTitle(null, null, null, null)
    }
}