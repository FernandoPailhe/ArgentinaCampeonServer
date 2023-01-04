package com.ferpa.plugins

import com.ferpa.data.matches.MatchesController
import com.ferpa.data.moments.MomentsController
import com.ferpa.data.photographer.PhotographersController
import com.ferpa.data.photos.PhotosController
import com.ferpa.data.players.PlayersController
import com.ferpa.routes.*
import io.ktor.routing.*
import io.ktor.application.*
import io.ktor.http.content.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {

    val photosController by inject<PhotosController>()
    val playersController by inject<PlayersController>()
    val matchesController by inject<MatchesController>()
    val photographersController by inject<PhotographersController>()
    val momentsController by inject<MomentsController>()
    install(Routing){
        getLastUpdatesDates(photosController)
        getNewPhotos(photosController)
        photosTitle(photosController)
        bestPhotos(photosController)
        // Photo routes
        photos(photosController)
        newPhoto(photosController)
        deletePhoto(photosController)
        postVote(photosController)
        photoById(photosController)
        photosByPlayer(photosController)
        photosByMatch(photosController)
        photosByTag(photosController)
        photosByPhotographer(photosController)
        photosByMoment(photosController)
        updatePhoto(photosController)
        // Player routes
        players(playersController)
        newPlayer(playersController)
        playerById(playersController)
        updatePlayer(playersController)
        deletePlayer(playersController)
        // Match routes
        matches(matchesController)
        newMatch(matchesController)
        matchById(matchesController)
        updateMatch(matchesController)
        deleteMatch(matchesController)
        // Photographer routes
        photographers(photographersController)
        newPhotographer(photographersController)
        photographerById(photographersController)
        updatePhotographer(photographersController)
        deletePhotographer(photographersController)
        // Moment routes
        moments(momentsController)
        momentsByDate(momentsController)
        newMoment(momentsController)
        momentById(momentsController)
        updateMoment(momentsController)
        deleteMoment(momentsController)
    }

    routing {
        static {
            resources("static")
        }
    }

}
