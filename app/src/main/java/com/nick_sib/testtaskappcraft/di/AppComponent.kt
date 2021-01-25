package com.nick_sib.testtaskappcraft.di

import com.nick_sib.testtaskappcraft.di.modules.ApiModule
import com.nick_sib.testtaskappcraft.di.modules.AppModule
import com.nick_sib.testtaskappcraft.di.modules.CiceroneModule
import com.nick_sib.testtaskappcraft.di.network.modules.NetworkModule
import com.nick_sib.testtaskappcraft.di.network.NetworkSubComponent
import com.nick_sib.testtaskappcraft.mvp.preseter.MainPresenter
import com.nick_sib.testtaskappcraft.mvp.preseter.NetworkAndDatabasePresenter
import com.nick_sib.testtaskappcraft.ui.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        ApiModule::class,
        CiceroneModule::class,
       // NetworkModule::class,
    ]
)

interface AppComponent {
    fun networkSubComponent() : NetworkSubComponent

    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    //fun inject(networkAndDatabasePresenter: NetworkAndDatabasePresenter)


}