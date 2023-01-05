package com.ferpa.routes

import com.ferpa.data.photographer.PhotographersController
import com.ferpa.data.photos.PhotosController
import com.ferpa.utils.Constants
import com.ferpa.utils.Constants.PHOTOGRAPHER_BASE_ROUTE
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*


fun Route.photographers(controller: PhotographersController) {
    get(PHOTOGRAPHER_BASE_ROUTE) {
        val getFrom = call.request.queryParameters["getFrom"]
        call.respond(
            HttpStatusCode.OK,
            controller.getAll(getFrom)
        )
    }
}

fun Route.photographerById(controller: PhotographersController) {
    get ("${PHOTOGRAPHER_BASE_ROUTE}/{id}") {
        val id = call.parameters["id"] ?: ""
        val response = controller.getPhotographerById(id) ?: HttpStatusCode.NoContent
        call.respond(
            HttpStatusCode.OK,
            response
        )
    }
}

fun Route.newPhotographer(controller: PhotographersController) {
    post("${PHOTOGRAPHER_BASE_ROUTE}/new") {
        controller.insertPhotographer(call.receive())
        call.respond(
            HttpStatusCode.Accepted
        )
    }
}

fun Route.updatePhotographer(controller: PhotographersController, photosController: PhotosController) {
    post("${PHOTOGRAPHER_BASE_ROUTE}/update") {
        if (controller.updatePhotographer(call.receive(), photosController)) {
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

fun Route.deletePhotographer(controller: PhotographersController) {
    delete("${PHOTOGRAPHER_BASE_ROUTE}/{id}") {
        if (call.request.queryParameters["deleteKey"] == Constants.DELETE_KEY){
            val id = call.parameters["id"] ?: ""
            if (controller.deleteOne(id)) {
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