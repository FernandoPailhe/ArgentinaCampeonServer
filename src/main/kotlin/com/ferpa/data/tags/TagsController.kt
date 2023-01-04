package com.ferpa.data.tags

import com.ferpa.data.model.Tag

class TagsController(
    private val dataSource: TagDataSource,
) {

    suspend fun getAll(getFrom: String? = ""): List<Tag> {
        return dataSource.getAll(getFrom)
    }

    suspend fun insertOne(item: Tag) {
        return dataSource.insertOne(item)
    }

    suspend fun getOneById(id: String): Tag? {
        return dataSource.getOneById(id)
    }

    suspend fun updateOne(item: Tag): Boolean {
        return dataSource.updateOne(item)
    }

    suspend fun deleteOne(id: String): Boolean {
        return dataSource.deleteOneById(id)
    }

}