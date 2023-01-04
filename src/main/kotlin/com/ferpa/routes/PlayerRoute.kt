package com.ferpa.routes


import com.ferpa.data.players.PlayersController
import com.ferpa.utils.Constants
import com.ferpa.utils.Constants.PLAYER_BASE_ROUTE
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.players(controller: PlayersController) {
    get(PLAYER_BASE_ROUTE) {
        val getFrom = call.request.queryParameters["getFrom"]
        call.respond(
            HttpStatusCode.OK,
            controller.getAll(getFrom)
        )
    }
}

fun Route.playerById(controller: PlayersController) {
    get ("${PLAYER_BASE_ROUTE}/{id}") {
        val id = call.parameters["id"] ?: ""
        val response = controller.getPlayerById(id) ?: HttpStatusCode.NoContent
        call.respond(
            HttpStatusCode.OK,
            response
        )
    }
}

fun Route.newPlayer(controller: PlayersController) {
    post("${PLAYER_BASE_ROUTE}/new") {
        controller.insertPlayer(call.receive())
        call.respond(
            HttpStatusCode.Accepted
        )
    }
}

fun Route.updatePlayer(controller: PlayersController) {
    post("${PLAYER_BASE_ROUTE}/update") {
        if (controller.updatePlayer(call.receive())) {
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

fun Route.deletePlayer(controller: PlayersController){
    delete("${PLAYER_BASE_ROUTE}/{id}") {
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

