package com.ferpa.routes


import com.ferpa.data.photos.PhotosController
import com.ferpa.utils.Constants.DELETE_KEY
import com.ferpa.utils.Constants.MATCH_BASE_ROUTE
import com.ferpa.utils.Constants.MOMENT_BASE_ROUTE
import com.ferpa.utils.Constants.PHOTOGRAPHER_BASE_ROUTE
import com.ferpa.utils.Constants.PHOTO_BASE_ROUTE
import com.ferpa.utils.Constants.PLAYER_BASE_ROUTE
import com.ferpa.utils.Constants.TAG_BASE_ROUTE
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

private const val BASE_URL = "http://192.168.100.4:8080"

// "http:// " aca va el ip local ":8080" en este caso 192.168.100.4
// "http://10.0.2.2:8080" con esta direccion es para probarlo desde el emulador


fun Route.getLastUpdatesDates(
    photosController: PhotosController
) {
    get("${PHOTO_BASE_ROUTE}/lastUpdatesDates") {
        call.respond(
            HttpStatusCode.OK,
            photosController.getLastUpdatesDates()
        )
    }
}

fun Route.getNewPhotos(photosController: PhotosController) {
    get(PHOTO_BASE_ROUTE) {
        val updateFrom = call.request.queryParameters["updateFrom"]
        call.respond(
            HttpStatusCode.OK,
            photosController.getPhotos(updateFrom)
        )
    }
}

fun Route.photos(photosController: PhotosController) {
    get(PHOTO_BASE_ROUTE) {
        call.respond(
            HttpStatusCode.OK,
            photosController.getAllPhotos()
        )
    }
}

fun Route.bestPhotos(
    photosController: PhotosController,
) {
    get("${PHOTO_BASE_ROUTE}/best") {
        call.respond(
            HttpStatusCode.OK,
            photosController.getAllPhotos()
        )
    }
}

fun Route.photosTitle(
    photosController: PhotosController,
) {
    get("${PHOTO_BASE_ROUTE}/versus") {
        val photosTitle = photosController.getVersusPhotos()
        call.respond(
            HttpStatusCode.OK,
            photosTitle
        )
    }
}

fun Route.photosByPlayer(
    photosController: PhotosController,
) {
    get("${PHOTO_BASE_ROUTE}${PLAYER_BASE_ROUTE}/{playerId}") {
        val playerId = call.parameters["playerId"] ?: ""
        call.respond(
            HttpStatusCode.OK,
            photosController.getPhotosByPlayer(playerId)
        )
    }
}

fun Route.photosByMatch(
    photosController: PhotosController,
) {
    get("${PHOTO_BASE_ROUTE}${MATCH_BASE_ROUTE}/{matchId}") {
        val matchId = call.parameters["matchId"] ?: ""
        call.respond(
            HttpStatusCode.OK,
            photosController.getPhotosByMatch(matchId)
        )
    }
}

fun Route.photosByTag(
    photosController: PhotosController,
) {
    get("${PHOTO_BASE_ROUTE}${TAG_BASE_ROUTE}/{tag}") {
        val tag = call.parameters["tag"] ?: ""
        call.respond(
            HttpStatusCode.OK,
            photosController.getPhotosByTag(tag)
        )
    }
}

fun Route.photosByPhotographer(
    photosController: PhotosController,
) {
    get("${PHOTO_BASE_ROUTE}${PHOTOGRAPHER_BASE_ROUTE}/{photographerId}") {
        val photographerId = call.parameters["photographerId"] ?: ""
        call.respond(
            HttpStatusCode.OK,
            photosController.getPhotosByPhotographer(photographerId)
        )
    }
}

fun Route.photosByMoment(
    photosController: PhotosController,
) {
    get("${PHOTO_BASE_ROUTE}${MOMENT_BASE_ROUTE}/{momentId}") {
        val momentId = call.parameters["momentId"] ?: ""
        call.respond(
            HttpStatusCode.OK,
            photosController.getPhotosByMoment(momentId)
        )
    }
}

fun Route.photoById(
    controller: PhotosController,
) {
    get("${PHOTO_BASE_ROUTE}/id/{id}") {
        val id = call.parameters["id"] ?: ""
        val response = controller.getPhotoById(id)
        call.respond(
            HttpStatusCode.OK,
            response ?: HttpStatusCode.NoContent
        )
    }
}

fun Route.newPhoto(photosController: PhotosController) {
    post("${PHOTO_BASE_ROUTE}/new") {
        photosController.insertPhoto(call.receive())
        call.respond(
            HttpStatusCode.Accepted
        )
    }
}

fun Route.updatePhoto(photosController: PhotosController) {
    post("${PHOTO_BASE_ROUTE}/update") {
        if (photosController.updatePhoto(call.receive())) {
            call.respond(
                HttpStatusCode.Accepted
            )
        } else {
            call.respond(
                HttpStatusCode.NotImplemented
            )
        }
    }
}

fun Route.postVote(photosController: PhotosController) {
    post("${PHOTO_BASE_ROUTE}/vote") {
        if (photosController.postVote(call.receive())) {
            call.respond(
                HttpStatusCode.Accepted
            )
        } else {
            call.respond(
                HttpStatusCode.NotImplemented
            )
        }

    }
}

fun Route.deletePhoto(photosController: PhotosController) {
    delete("${PHOTO_BASE_ROUTE}/{id}") {
        if (call.request.queryParameters["deleteKey"] == DELETE_KEY) {
            val id = call.parameters["id"] ?: ""
            if (photosController.deleteOne(id)) {
                call.respond(
                    HttpStatusCode.Accepted
                )
            } else {
                call.respond(
                    HttpStatusCode.NotImplemented
                )
            }
        } else {
            call.respond(
                HttpStatusCode.Unauthorized
            )
        }
    }
}