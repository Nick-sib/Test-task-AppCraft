package com.nick_sib.testtaskappcraft.di.database


import com.nick_sib.testtaskappcraft.di.albumdetail.database.AlbumDetailDatabaseSubComponent
import com.nick_sib.testtaskappcraft.di.database.modules.DatabaseCacheModule
import com.nick_sib.testtaskappcraft.mvp.preseter.NetworkAndDatabasePresenter
import dagger.Subcomponent


@DatabaseScope
@Subcomponent(
    modules = [
        DatabaseCacheModule::class
    ]
)
interface DatabaseSubComponent {
    fun albumDetailDatabaseSubComponent() : AlbumDetailDatabaseSubComponent

    fun inject(networkAndDatabasePresenter: NetworkAndDatabasePresenter)
}
