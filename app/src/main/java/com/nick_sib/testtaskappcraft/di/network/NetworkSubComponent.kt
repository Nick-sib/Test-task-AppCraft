package com.nick_sib.testtaskappcraft.di.network

import com.nick_sib.testtaskappcraft.di.albumdetail.AlbumDetailSubComponent
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
    fun albumDetailSubComponent() : AlbumDetailSubComponent

    fun inject(networkAndDatabasePresenter: NetworkAndDatabasePresenter)
}

