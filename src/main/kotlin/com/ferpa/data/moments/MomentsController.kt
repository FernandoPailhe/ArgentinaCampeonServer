package com.ferpa.data.moments

import com.ferpa.data.model.Moment

class MomentsController(
    private val dataSource: MomentDataSource,
) {

    suspend fun getAll(getFrom: String? = ""): List<Moment> {
        return dataSource.getAllMoments(getFrom)
    }

    suspend fun getMomentsByDate(date: String): List<Moment> {
        return dataSource.getMomentsByDate(date)
    }

    suspend fun insertPlayer(moment: Moment) {
        return dataSource.insertMoment(moment)
    }

    suspend fun getMomentById(momentId: String): Moment? {
        return dataSource.getMomentById(momentId)
    }

    suspend fun updateMoment(moment: Moment): Boolean {
        return dataSource.updateMoment(moment)
    }

    suspend fun deleteOne(id: String): Boolean {
        return dataSource.deleteOneById(id)
    }

}