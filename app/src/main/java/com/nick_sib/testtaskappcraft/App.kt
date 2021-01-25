package com.nick_sib.testtaskappcraft

import android.app.Application
import com.nick_sib.testtaskappcraft.di.AppComponent
import com.nick_sib.testtaskappcraft.di.DaggerAppComponent
import com.nick_sib.testtaskappcraft.di.albumdetail.AlbumDetailSubComponent
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
    private var albumDetailSubComponent: AlbumDetailSubComponent? = null

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

    fun initAlbumDetailSubComponent() = networkSubComponent?.albumDetailSubComponent().also {
        albumDetailSubComponent = it
    }

    fun releaseAlbumDetailSubComponent() {
        albumDetailSubComponent = null
    }

}