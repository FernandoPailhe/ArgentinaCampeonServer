package com.ferpa.data.app_info

import com.ferpa.data.model.AppInfo

interface AppInfoDataSource {

    suspend fun getAll(getFrom: String?): List<AppInfo>

    suspend fun insertOne(item: AppInfo)

    suspend fun getOneById(id: String): AppInfo?

    suspend fun updateOne(item: AppInfo): Boolean

    suspend fun deleteOneById(id: String): Boolean

}