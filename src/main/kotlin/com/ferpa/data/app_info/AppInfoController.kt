package com.ferpa.data.app_info

import com.ferpa.data.model.AppInfo

class AppInfoController(
    private val dataSource: AppInfoDataSource,
) {

    suspend fun getAll(getFrom: String? = ""): List<AppInfo> {
        return dataSource.getAll(getFrom)
    }

    suspend fun insertOne(item: AppInfo) {
        return dataSource.insertOne(item)
    }

    suspend fun getOneById(id: String): AppInfo? {
        return dataSource.getOneById(id)
    }

    suspend fun updateOne(item: AppInfo): Boolean {
        return dataSource.updateOne(item)
    }

    suspend fun deleteOne(id: String): Boolean {
        return dataSource.deleteOneById(id)
    }

}