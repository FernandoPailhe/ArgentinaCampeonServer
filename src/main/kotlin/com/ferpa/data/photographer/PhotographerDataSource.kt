package com.ferpa.data.photographer

import com.ferpa.data.model.Photographer

interface PhotographerDataSource {

    suspend fun getAllPhotographers(getFrom: String?): List<Photographer>

    suspend fun insertPhotographer(photographer: Photographer)

    suspend fun getPhotographerById(photographerId: String): Photographer?

    suspend fun updatePhotographer(photographer: Photographer): Boolean

    suspend fun deleteOneById(id: String): Boolean

}