package com.nick_sib.testtaskappcraft.di.albumdetail.database


import com.nick_sib.testtaskappcraft.di.albumdetail.database.modules.AlbumDetailCacheModule
import com.nick_sib.testtaskappcraft.di.albumdetail.database.modules.RepoAlbumsDetailModule
import com.nick_sib.testtaskappcraft.mvp.preseter.AlbumDetailPresenter

import dagger.Subcomponent

@AlbumDetailDatabaseScope
@Subcomponent(
    modules = [
        RepoAlbumsDetailModule::class,
        AlbumDetailCacheModule::class
    ]
)

interface AlbumDetailDatabaseSubComponent {
    fun inject(albumDetailPresenter: AlbumDetailPresenter)
}