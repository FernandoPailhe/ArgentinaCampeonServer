package com.ferpa.routes


import com.ferpa.data.model.CustomQuery
import com.ferpa.data.model.PhotoState
import com.ferpa.data.photos.PhotosController
import com.ferpa.utils.Constants
import com.ferpa.utils.Constants.BEST_PHOTO_LIMIT
import com.ferpa.utils.Constants.DELETE_KEY
import com.ferpa.utils.Constants.MATCH_BASE_ROUTE
import com.ferpa.utils.Constants.MOMENT_BASE_ROUTE
import com.ferpa.utils.Constants.PHOTOGRAPHER_BASE_ROUTE
import com.ferpa.utils.Constants.PHOTO_BASE_ROUTE
import com.ferpa.utils.Constants.PLAYER_BASE_ROUTE
import com.ferpa.utils.Constants.POST_KEY
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
    photosController: PhotosController,
) {
    get("${PHOTO_BASE_ROUTE}/lastUpdatesDates") {
        call.respond(
            HttpStatusCode.OK,
            photosController.getLastUpdatesDates()
        )
    }
}

fun Route.photos(photosController: PhotosController) {
    get(PHOTO_BASE_ROUTE) {
        val getFrom = call.request.queryParameters["getFrom"]
        call.respond(
            HttpStatusCode.OK,
            photosController.getPhotos(getFrom)
        )
    }
}

fun Route.updatePhotos(photosController: PhotosController) {
    get("$PHOTO_BASE_ROUTE/updates") {
        val getFrom = call.request.queryParameters["getFrom"]
        call.respond(
            HttpStatusCode.OK,
            photosController.getUpdatePhotos(getFrom)
        )
    }
}

fun Route.rankUpdates(photosController: PhotosController) {
    get("$PHOTO_BASE_ROUTE/rankUpdates") {
        val getFrom = call.request.queryParameters["getFrom"]
        call.respond(
            HttpStatusCode.OK,
            photosController.getRankUpdates(getFrom)
        )
    }
}

fun Route.bestPhotos(
    photosController: PhotosController,
) {
    get("${PHOTO_BASE_ROUTE}/best") {
        val limit = (call.request.queryParameters["limit"])?.toInt() ?: BEST_PHOTO_LIMIT
        val page = (call.request.queryParameters["page"])?.toInt() ?: 0
        call.respond(
            HttpStatusCode.OK,
            photosController.getBestPhotos(limit, page)
        )
    }
}

fun Route.worstPhotos(
    photosController: PhotosController,
) {
    get("${PHOTO_BASE_ROUTE}/worst") {
        call.respond(
            HttpStatusCode.OK,
            photosController.getWorstPhotos()
        )
    }
}

fun Route.photosByPlayer(
    photosController: PhotosController,
) {
    get("${PHOTO_BASE_ROUTE}${PLAYER_BASE_ROUTE}/{playerId}") {
        val playerId = call.parameters["playerId"] ?: ""
        val best = (call.request.queryParameters["best"])?.toInt() ?: 0
        call.respond(
            HttpStatusCode.OK,
            photosController.getPhotosByPlayer(playerId, best)
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

fun Route.photosByState(
    photosController: PhotosController,
) {
    get("$PHOTO_BASE_ROUTE/state") {
        val rarity = call.request.queryParameters["rarity"]?.toInt() ?: PhotoState.HideState.rarity
        call.respond(
            HttpStatusCode.OK,
            photosController.getPhotosByState(rarity)
        )
    }
}

fun Route.photosByCustomQuery(
    photosController: PhotosController,
) {
    get("${PHOTO_BASE_ROUTE}/customQuery") {
        val customQuery = CustomQuery(
            playerId = call.request.queryParameters["playerId"] ?: "",
            matchId = call.request.queryParameters["matchId"] ?: "",
            tagId = call.request.queryParameters["tagId"] ?: "",
            photographerId = call.request.queryParameters["photographerId"] ?: "",
            momentId = call.request.queryParameters["momentId"] ?: "",
            sortBy = call.request.queryParameters["sortBy"] ?: "",
            sortAscending = call.request.queryParameters["sortAscending"] == "true"
        )
        call.respond(
            HttpStatusCode.OK,
            photosController.getPhotosByCustomQuery(customQuery)
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

fun Route.massiveAdd(photosController: PhotosController) {
    post("${PHOTO_BASE_ROUTE}/massiveAdd") {
        if (photosController.massiveAdd(call.receive())) {
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

fun Route.updatePhoto(photosController: PhotosController) {
    post("${PHOTO_BASE_ROUTE}/update") {
        val full = (call.request.queryParameters["full"])?.toInt() ?: 0
        if (full == 1) {
            if (photosController.fullUpdatePhoto(call.receive())) {
                call.respond(
                    HttpStatusCode.Accepted
                )
            } else {
                call.respond(
                    HttpStatusCode.NotImplemented
                )
            }
        } else if (photosController.softUpdatePhoto(call.receive())) {
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

fun Route.resetRank(photosController: PhotosController) {
    post("${PHOTO_BASE_ROUTE}/resetRank") {
        if (call.request.queryParameters["postkey"] == POST_KEY) {
            val votes = call.parameters["votes"]?.toInt() ?: Constants.NEW_VOTES_DEFAULT_VALUE
            val versus = call.parameters["versus"]?.toInt() ?: Constants.NEW_VERSUS_DEFAULT_VALUE
            val random = call.parameters["random"]?.toInt() ?: Constants.RANDOM_RANGE_DEFAULT_VALUE
            if (photosController.resetRank(votes, versus, random)) {
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

fun Route.updateState(photosController: PhotosController) {
    post("${PHOTO_BASE_ROUTE}/state/{id}") {
        if (call.request.queryParameters["postkey"] == POST_KEY) {
            val id = call.parameters["id"] ?: ""
            val rarity = call.parameters["rarity"]?.toInt() ?: PhotoState.AvailableState.rarity
            if (photosController.updateState(id, rarity)) {
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

/*
fun Route.multipartUpload() {
    /**
     * Example
     */
    var fileDescription = ""
    var fileName = ""

    post("/upload") {
        val multipartData = call.receiveMultipart()

        multipartData.forEachPart { part ->
            when (part) {
                is PartData.FormItem -> {
                    fileDescription = part.value
                }

                is PartData.FileItem -> {
                    fileName = part.originalFileName as String
                    val fileBytes = part.streamProvider().readBytes()
                    File("uploads/$fileName").writeBytes(fileBytes)
                }

                else -> {}
            }
            part.dispose()
        }

        call.respondText("$fileDescription is uploaded to 'uploads/$fileName'")
    }

}
 */