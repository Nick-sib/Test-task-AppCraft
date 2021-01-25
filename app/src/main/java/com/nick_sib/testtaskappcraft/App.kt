package com.nick_sib.testtaskappcraft

import android.app.Application
import com.nick_sib.testtaskappcraft.di.AppComponent
import com.nick_sib.testtaskappcraft.di.DaggerAppComponent
import com.nick_sib.testtaskappcraft.di.albumdetail.database.AlbumDetailDatabaseSubComponent
import com.nick_sib.testtaskappcraft.di.albumdetailnetwork.AlbumDetailNetworkSubComponent
import com.nick_sib.testtaskappcraft.di.database.DatabaseSubComponent
import com.nick_sib.testtaskappcraft.di.modules.AppModule
import com.nick_sib.testtaskappcraft.di.network.NetworkSubComponent

class App: Application() {

    companion object {
        lateinit var instance: App
            private set
    }

    lateinit var appComponent: AppComponent
        private set

    private var networkSubComponent: NetworkSubComponent? = null
    private var albumDetailNetworkSubComponent: AlbumDetailNetworkSubComponent? = null
    private var databaseSubComponent: DatabaseSubComponent? = null
    private var albumDetailDatabaseSubComponent: AlbumDetailDatabaseSubComponent? = null

    override fun onCreate() {
        super.onCreate()
        instance = this

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }


    fun initNetworkSubComponent() = appComponent.networkSubComponent().also {
        networkSubComponent = it
    }

    fun releaseNetworkSubComponent() {
        networkSubComponent = null
    }

    fun initAlbumDetailNetworkSubComponent() = networkSubComponent?.albumDetailSubComponent().also {
        albumDetailNetworkSubComponent = it
    }

    fun releaseAlbumDetailNetworkSubComponent() {
        albumDetailNetworkSubComponent = null
    }

    fun initDatabaseSubComponent() = appComponent.databaseSubComponent().also {
        databaseSubComponent = it
    }

    fun releaseDatabaseSubComponent() {
        databaseSubComponent = null
    }

    fun initAlbumDetailDatabaseSubComponent() = databaseSubComponent?.albumDetailDatabaseSubComponent().also {
        albumDetailDatabaseSubComponent = it
    }

    fun releaseAlbumDetailDatabaseSubComponent() {
        albumDetailDatabaseSubComponent = null
    }

}