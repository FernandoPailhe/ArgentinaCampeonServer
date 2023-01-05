package com.ferpa.data.photographer

import com.ferpa.data.model.Photographer
import com.ferpa.data.photos.PhotosController

interface PhotographerDataSource {

    suspend fun getAllPhotographers(getFrom: String?): List<Photographer>

    suspend fun insertPhotographer(photographer: Photographer)

    suspend fun getOneById(photographerId: String): Photographer?

    suspend fun updatePhotographer(photographer: Photographer, photosController: PhotosController): Boolean

    suspend fun deleteOneById(id: String): Boolean

}