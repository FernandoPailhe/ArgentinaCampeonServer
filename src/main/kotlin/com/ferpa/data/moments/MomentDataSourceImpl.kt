package com.ferpa.data.moments

import com.ferpa.data.model.*
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq
import org.litote.kmongo.gt

class MomentDataSourceImpl(
    private val db: CoroutineDatabase
) : MomentDataSource {

    private val collection = db.getCollection<Moment>()

    override suspend fun getAllMoments(getFrom: String?): List<Moment> {
        return if (getFrom.isNullOrEmpty()){
            collection.find()
                .ascendingSort(Moment::date, Moment::matchTime)
                .toList()
        } else {
            collection.find(Moment::lastUpdate gt getFrom)
                .descendingSort(Moment::lastUpdate)
                .toList()
        }
    }

    override suspend fun getMomentsByDate(date: String): List<Moment> {
        return collection.find(Moment::date eq date)
            .ascendingSort(Moment::matchTime)
            .toList()
    }

    override suspend fun insertMoment(moment: Moment) {
        collection.insertOne(moment.addUUID())
    }

    override suspend fun getMomentById(momentId: String): Moment? {
        return collection.findOne(Moment::id eq momentId)
    }

    override suspend fun updateMoment(moment: Moment): Boolean {
        return try {
            collection.updateOne(Moment::id eq moment.id, moment.updateMoment()).wasAcknowledged()
            true
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun deleteOneById(id: String): Boolean {
        return collection.deleteOne(Moment::id eq id).wasAcknowledged()
    }

}