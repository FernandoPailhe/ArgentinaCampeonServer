package com.ferpa.routes

import com.ferpa.data.matches.MatchesController
import com.ferpa.data.moments.MomentsController
import com.ferpa.utils.Constants
import com.ferpa.utils.Constants.MOMENT_BASE_ROUTE
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.moments(controller: MomentsController) {
    get(MOMENT_BASE_ROUTE) {
        val getFrom = call.request.queryParameters["getFrom"]
        call.respond(
            HttpStatusCode.OK,
            controller.getAll(getFrom)
        )
    }
}

fun Route.momentsByDate(controller: MomentsController) {
    get("${MOMENT_BASE_ROUTE}/{date}") {
        val date = call.parameters["date"] ?: ""
        call.respond(
            HttpStatusCode.OK,
            controller.getMomentsByDate(date)
        )
    }
}

fun Route.momentById(controller: MomentsController) {
    get ("${MOMENT_BASE_ROUTE}/{id}") {
        val id = call.parameters["id"] ?: ""
        val response = controller.getMomentById(id) ?: HttpStatusCode.NoContent
        call.respond(
            HttpStatusCode.OK,
            response
        )
    }
}

fun Route.newMoment(controller: MomentsController) {
    post("${MOMENT_BASE_ROUTE}/new") {
        controller.insertPlayer(call.receive())
        call.respond(
            HttpStatusCode.Accepted
        )
    }
}

fun Route.updateMoment(controller: MomentsController) {
    post("${MOMENT_BASE_ROUTE}/update") {
        if (controller.updateMoment(call.receive())) {
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

fun Route.deleteMoment(controller: MomentsController) {
    delete("${MOMENT_BASE_ROUTE}/{id}") {
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
