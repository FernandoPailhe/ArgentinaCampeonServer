package com.ferpa.data.moments

import com.ferpa.data.model.Moment
import com.ferpa.data.photos.PhotosController

interface MomentDataSource {

    suspend fun getAllMoments(getFrom: String?): List<Moment>

    suspend fun getMomentsByDate(date: String): List<Moment>

    suspend fun insertMoment(moment: Moment)

    suspend fun getOneById(momentId: String): Moment?

    suspend fun updateMoment(moment: Moment, photosController: PhotosController): Boolean

    suspend fun deleteOneById(id: String): Boolean
}