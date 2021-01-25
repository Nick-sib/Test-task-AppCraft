package com.nick_sib.testtaskappcraft.di.network

import com.nick_sib.testtaskappcraft.di.network.modules.NetworkModule
import com.nick_sib.testtaskappcraft.mvp.preseter.NetworkAndDatabasePresenter
import dagger.Subcomponent


@NetworkScope
@Subcomponent(
    modules = [
        NetworkModule::class
    ]
)

interface NetworkSubComponent {
    fun inject(networkAndDatabasePresenter: NetworkAndDatabasePresenter)
}

