package com.nick_sib.testtaskappcraft.di.albumdetail

import com.nick_sib.testtaskappcraft.di.albumdetail.modules.AlbumDetailCacheModule
import com.nick_sib.testtaskappcraft.di.albumdetail.modules.RepoAlbumsDetailModule
import com.nick_sib.testtaskappcraft.mvp.preseter.AlbumDetailPresenter
import dagger.Subcomponent

@AlbumDetailScope
@Subcomponent(
    modules = [
        RepoAlbumsDetailModule::class,
        AlbumDetailCacheModule::class
    ]
)

interface AlbumDetailSubComponent {
    fun inject(albumDetailPresenter: AlbumDetailPresenter)
}