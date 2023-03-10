package com.ferpa.data.tags

import com.ferpa.data.model.*
import com.ferpa.data.photos.PhotosController
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq
import org.litote.kmongo.gt

class TagDataSourceImpl(
    private val db: CoroutineDatabase,
) : TagDataSource {

    private val collection = db.getCollection<Tag>()

    override suspend fun getAll(getFrom: String?): List<Tag> {
        return if (getFrom.isNullOrEmpty()) {
            collection.find()
                .descendingSort(Tag::id)
                .toList()
        } else {
            collection.find(Tag::lastUpdate gt getFrom)
                .descendingSort(Tag::lastUpdate)
                .toList()
        }
    }

    override suspend fun insertOne(tag: Tag) {
        collection.insertOne(tag.new())
    }

    override suspend fun getOneById(id: String): Tag? {
        return collection.findOne(Tag::id eq id)
    }

    override suspend fun updateOne(tag: Tag, photosController: PhotosController): Boolean {
        return try {
            if (haveToUpdate(tag)) photosController.updateAllTags(tag)
            collection.updateOne(Tag::id eq tag.id, tag.update()).wasAcknowledged()
            true
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun deleteOneById(id: String): Boolean {
        return collection.deleteOne(Tag::id eq id).wasAcknowledged()
    }

    private suspend fun haveToUpdate(newItem: Tag): Boolean {
        val oldItem = getOneById(newItem.id)
        return (oldItem != newItem)
    }
}