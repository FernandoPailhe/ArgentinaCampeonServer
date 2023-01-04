package com.ferpa.routes


import com.ferpa.data.tags.TagController
import com.ferpa.utils.Constants
import com.ferpa.utils.Constants.TAG_BASE_ROUTE
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.tags(controller: TagController) {
    get(TAG_BASE_ROUTE) {
        val getFrom = call.request.queryParameters["getFrom"]
        call.respond(
            HttpStatusCode.OK,
            controller.getAll(getFrom)
        )
    }
}

fun Route.tagById(controller: TagController) {
    get ("${TAG_BASE_ROUTE}/{id}") {
        val id = call.parameters["id"] ?: ""
        val response = controller.getOneById(id) ?: HttpStatusCode.NoContent
        call.respond(
            HttpStatusCode.OK,
            response
        )
    }
}

fun Route.newTag(controller: TagController) {
    post("${TAG_BASE_ROUTE}/new") {
        controller.insertOne(call.receive())
        call.respond(
            HttpStatusCode.Accepted
        )
    }
}

fun Route.updateTag(controller: TagController) {
    post("${TAG_BASE_ROUTE}/update") {
        if (controller.updateOne(call.receive())) {
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

fun Route.deleteTag(controller: TagController){
    delete("${TAG_BASE_ROUTE}/{id}") {
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

