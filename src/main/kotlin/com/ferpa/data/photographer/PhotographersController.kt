package com.ferpa.data.photographer

import com.ferpa.data.model.Match
import com.ferpa.data.model.Photographer

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
        return dataSource.getPhotographerById(photographerId)
    }

    suspend fun updatePhotographer(photographer: Photographer): Boolean {
        return dataSource.updatePhotographer(photographer)
    }

    suspend fun deleteOne(id: String): Boolean {
        return dataSource.deleteOneById(id)
    }

}