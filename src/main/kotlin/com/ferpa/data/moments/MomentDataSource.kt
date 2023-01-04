package com.ferpa.data.moments

import com.ferpa.data.model.Moment
import com.ferpa.data.model.Player

interface MomentDataSource {

    suspend fun getAllMoments(getFrom: String?): List<Moment>

    suspend fun getMomentsByDate(date: String): List<Moment>

    suspend fun insertMoment(moment: Moment)

    suspend fun getMomentById(momentId: String): Moment?

    suspend fun updateMoment(moment: Moment): Boolean

    suspend fun deleteOneById(id: String): Boolean
}