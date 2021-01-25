package com.nick_sib.testtaskappcraft

import android.app.Application
import com.nick_sib.testtaskappcraft.di.AppComponent
import com.nick_sib.testtaskappcraft.di.DaggerAppComponent
import com.nick_sib.testtaskappcraft.di.modules.AppModule

class App: Application() {

    companion object {
        lateinit var instance: App
            private set
    }

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}