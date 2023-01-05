package com.ferpa.routes

import com.ferpa.data.matches.MatchesController
import com.ferpa.data.photos.PhotosController
import com.ferpa.utils.Constants
import com.ferpa.utils.Constants.MATCH_BASE_ROUTE
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.matches(controller: MatchesController) {
    get(MATCH_BASE_ROUTE) {
        val getFrom = call.request.queryParameters["getFrom"]
        call.respond(
            HttpStatusCode.OK,
            controller.getAll(getFrom)
        )
    }
}

fun Route.matchById(controller: MatchesController) {
    get ("${MATCH_BASE_ROUTE}/{id}") {
        val id = call.parameters["id"] ?: ""
        val response = controller.getMatchById(id) ?: HttpStatusCode.NoContent
        call.respond(
            HttpStatusCode.OK,
            response
        )
    }
}

fun Route.newMatch(controller: MatchesController) {
    post("${MATCH_BASE_ROUTE}/new") {
        controller.insertMatch(call.receive())
        call.respond(
            HttpStatusCode.Accepted
        )
    }
}

fun Route.updateMatch(controller: MatchesController, photosController: PhotosController) {
    post("${MATCH_BASE_ROUTE}/update") {
        if (controller.updateMatch(call.receive(), photosController)) {
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

fun Route.deleteMatch(controller: MatchesController) {
    delete("${MATCH_BASE_ROUTE}/{id}") {
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