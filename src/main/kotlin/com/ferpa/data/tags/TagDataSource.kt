package com.ferpa.data.tags

import com.ferpa.data.model.Tag

interface TagDataSource {

    suspend fun getAll(getFrom: String?): List<Tag>

    suspend fun insertOne(tag: Tag)

    suspend fun getOneById(id: String): Tag?

    suspend fun updateOne(tag: Tag): Boolean

    suspend fun deleteOneById(id: String): Boolean

}