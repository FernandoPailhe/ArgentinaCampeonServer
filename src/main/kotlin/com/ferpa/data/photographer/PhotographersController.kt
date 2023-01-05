package com.ferpa.data.photographer

import com.ferpa.data.model.Photographer
import com.ferpa.data.photos.PhotosController

class PhotographersController(
    private val dataSource: PhotographerDataSource,
) {

    suspend fun getAll(getFrom: String? = ""): List<Photographer> {
        return dataSource.getAllPhotographers(getFrom)
    }

    suspend fun insertPhotographer(photographer: Photographer) {
        return dataSource.insertPhotographer(photographer)
    }

    suspend fun getPhotographerById(photographerId: String): Photographer? {
        return dataSource.getOneById(photographerId)
    }

    suspend fun updatePhotographer(photographer: Photographer, photosController: PhotosController): Boolean {
        return dataSource.updatePhotographer(photographer, photosController)
    }

    suspend fun deleteOne(id: String): Boolean {
        return dataSource.deleteOneById(id)
    }

}