package com.ferpa.di

import com.ferpa.data.app_info.AppInfoController
import com.ferpa.data.app_info.AppInfoDataSource
import com.ferpa.data.app_info.AppInfoDataSourceImpl
import com.ferpa.data.matches.MatchDataSource
import com.ferpa.data.matches.MatchDataSourceImpl
import com.ferpa.data.matches.MatchesController
import com.ferpa.data.moments.MomentDataSource
import com.ferpa.data.moments.MomentDataSourceImpl
import com.ferpa.data.moments.MomentsController
import com.ferpa.data.photographer.PhotographerDataSource
import com.ferpa.data.photographer.PhotographerDataSourceImpl
import com.ferpa.data.photographer.PhotographersController
import com.ferpa.data.photos.PhotoDataSource
import com.ferpa.data.photos.PhotoDataSourceImpl
import com.ferpa.data.photos.PhotosController
import com.ferpa.data.players.PlayerDataSource
import com.ferpa.data.players.PlayerDataSourceImpl
import com.ferpa.data.players.PlayersController
import com.ferpa.data.tags.TagsController
import com.ferpa.data.tags.TagDataSource
import com.ferpa.data.tags.TagDataSourceImpl
import com.ferpa.utils.Constants
import org.koin.dsl.module
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

val mainModule = module {

    single {
        KMongo.createClient()
            .coroutine
            .getDatabase(Constants.DB_NAME)
    }
    single<PhotoDataSource> {
        PhotoDataSourceImpl(get())
    }
    single<PlayerDataSource> {
        PlayerDataSourceImpl(get())
    }
    single<MatchDataSource> {
        MatchDataSourceImpl(get())
    }
    single<PhotographerDataSource> {
        PhotographerDataSourceImpl(get())
    }
    single<TagDataSource> {
        TagDataSourceImpl(get())
    }
    single<MomentDataSource> {
        MomentDataSourceImpl(get())
    }
    single<AppInfoDataSource> {
        AppInfoDataSourceImpl(get())
    }
    single {
        PhotosController(get())
    }
    single {
        PlayersController(get())
    }
    single {
        MatchesController(get())
    }
    single {
        PhotographersController(get())
    }
    single {
        MomentsController(get())
    }
    single {
        TagsController(get())
    }
    single {
        AppInfoController(get())
    }

}