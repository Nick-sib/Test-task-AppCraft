package com.nick_sib.testtaskappcraft.di.albumdetail.modules

import com.nick_sib.testtaskappcraft.di.albumdetail.AlbumDetailScope

import com.nick_sib.testtaskappcraft.mvp.model.api.ILoadAlbums
import com.nick_sib.testtaskappcraft.mvp.model.netstate.INetworkStatus
import com.nick_sib.testtaskappcraft.mvp.model.repo.IRepoAlbumsDetail
import com.nick_sib.testtaskappcraft.mvp.model.repo.RepoAlbums
import dagger.Module
import dagger.Provides

@Module
class RepoAlbumsDetailModule {
    @AlbumDetailScope
    @Provides
    fun albumsRepo(
        api: ILoadAlbums,
        networkStatus: INetworkStatus,
    ): IRepoAlbumsDetail = RepoAlbums(api, networkStatus)

}


