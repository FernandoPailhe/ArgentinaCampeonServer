package com.ferpa.data.app_info

import com.ferpa.data.model.AppInfo
import com.ferpa.data.model.new
import com.ferpa.data.model.update
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq
import org.litote.kmongo.gt

class AppInfoDataSourceImpl(
    private val db: CoroutineDatabase,
) : AppInfoDataSource {

    private val collection = db.getCollection<AppInfo>()

    override suspend fun getAll(getFrom: String?): List<AppInfo> {
        return if (getFrom.isNullOrEmpty()) {
            collection.find()
                .descendingSort(AppInfo::id)
                .toList()
        } else {
            collection.find(AppInfo::lastUpdate gt getFrom)
                .descendingSort(AppInfo::lastUpdate)
                .toList()
        }
    }

    override suspend fun insertOne(item: AppInfo) {
        collection.insertOne(item.new())
    }

    override suspend fun getOneById(id: String): AppInfo? {
        return collection.findOne(AppInfo::id eq id)
    }

    override suspend fun updateOne(item: AppInfo): Boolean {
        return try {
            collection.updateOne(AppInfo::id eq item.id, item.update()).wasAcknowledged()
            true
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun deleteOneById(id: String): Boolean {
        return collection.deleteOne(AppInfo::id eq id).wasAcknowledged()
    }

}