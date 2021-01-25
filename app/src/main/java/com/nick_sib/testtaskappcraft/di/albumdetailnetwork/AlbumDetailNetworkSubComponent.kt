package com.nick_sib.testtaskappcraft.di.albumdetailnetwork

import com.nick_sib.testtaskappcraft.di.albumdetailnetwork.modules.AlbumDetailCacheModule
import com.nick_sib.testtaskappcraft.di.albumdetailnetwork.modules.RepoAlbumsDetailModule
import com.nick_sib.testtaskappcraft.mvp.preseter.AlbumDetailPresenter
import dagger.Subcomponent

@AlbumDetailNetworkScope
@Subcomponent(
    modules = [
        RepoAlbumsDetailModule::class,
        AlbumDetailCacheModule::class
    ]
)

interface AlbumDetailNetworkSubComponent {
    fun inject(albumDetailPresenter: AlbumDetailPresenter)
}