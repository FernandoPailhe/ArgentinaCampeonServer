package com.ferpa.plugins

import com.ferpa.data.app_info.AppInfoController
import com.ferpa.data.matches.MatchesController
import com.ferpa.data.moments.MomentsController
import com.ferpa.data.photographer.PhotographersController
import com.ferpa.data.photos.PhotosController
import com.ferpa.data.players.PlayersController
import com.ferpa.data.tags.TagsController
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
    val tagsController by inject<TagsController>()
    val appInfoController by inject<AppInfoController>()
    install(Routing){
        getLastUpdatesDates(photosController)
        // Photo routes
        photos(photosController)
        updatePhotos(photosController)
        rankUpdates(photosController)
        bestPhotos(photosController)
        newPhoto(photosController)
        deletePhoto(photosController)
        postVote(photosController)
        photoById(photosController)
        photosByPlayer(photosController)
        photosByMatch(photosController)
        photosByTag(photosController)
        photosByPhotographer(photosController)
        photosByMoment(photosController)
        photosByCustomQuery(photosController)
        updatePhoto(photosController)
        // Player routes
        players(playersController)
        newPlayer(playersController)
        playerById(playersController)
        updatePlayer(playersController, photosController)
        deletePlayer(playersController)
        // Match routes
        matches(matchesController)
        newMatch(matchesController)
        matchById(matchesController)
        updateMatch(matchesController, photosController)
        deleteMatch(matchesController)
        // Photographer routes
        photographers(photographersController)
        newPhotographer(photographersController)
        photographerById(photographersController)
        updatePhotographer(photographersController, photosController)
        deletePhotographer(photographersController)
        // Moment routes
        moments(momentsController)
        momentsByDate(momentsController)
        newMoment(momentsController)
        momentById(momentsController)
        updateMoment(momentsController, photosController)
        deleteMoment(momentsController)
        // Tag routes
        tags(tagsController)
        newTag(tagsController)
        updateTag(tagsController, photosController)
        tagById(tagsController)
        deleteTag(tagsController)
        // App Info routes
        appInfo(appInfoController)
        newAppInfo(appInfoController)
        updateAppInfo(appInfoController)
        appInfoById(appInfoController)
        deleteAppInfo(appInfoController)
    }

    routing {
        static {
            resources("static")
        }
    }

}
