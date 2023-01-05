package com.ferpa.data.photographer

import com.ferpa.data.model.*
import com.ferpa.data.photos.PhotosController
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq
import org.litote.kmongo.gt

class PhotographerDataSourceImpl(
    private val db: CoroutineDatabase,
) : PhotographerDataSource {

    private val collection = db.getCollection<Photographer>()

    override suspend fun getAllPhotographers(getFrom: String?): List<Photographer> {
        return if (getFrom.isNullOrEmpty()) {
            collection.find()
                .descendingSort(Photographer::lastUpdate)
                .toList()
        } else {
            collection.find(Photographer::lastUpdate gt getFrom)
                .descendingSort(Photographer::lastUpdate)
                .toList()
        }
    }

    override suspend fun insertPhotographer(photographer: Photographer) {
        collection.insertOne(photographer.addUUID())
    }

    override suspend fun getOneById(photographerId: String): Photographer? {
        return collection.findOne(Photographer::id eq photographerId)
    }

    override suspend fun updatePhotographer(photographer: Photographer, photosController: PhotosController): Boolean {
        return try {
            if (haveToUpdate(photographer)) photosController.updateAllPhotographerTitles(photographer.toPhotographerTitle())
            collection.updateOne(Photographer::id eq photographer.id, photographer.updatePhotographer())
                .wasAcknowledged()
            true
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun deleteOneById(id: String): Boolean {
        return collection.deleteOne(Moment::id eq id).wasAcknowledged()
    }

    private suspend fun haveToUpdate(newItem: Photographer): Boolean {
        val oldItem = newItem.id?.let { getOneById(it) }
        return (oldItem?.toPhotographerTitle() != newItem.toPhotographerTitle())
    }
}