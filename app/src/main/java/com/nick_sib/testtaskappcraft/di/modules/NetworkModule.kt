package com.nick_sib.testtaskappcraft.di.modules

import com.nick_sib.testtaskappcraft.mvp.model.api.ILoadAlbums
import com.nick_sib.testtaskappcraft.mvp.model.netstate.INetworkStatus
import com.nick_sib.testtaskappcraft.mvp.model.repo.IRepoAlbums
import com.nick_sib.testtaskappcraft.mvp.model.repo.RepoAlbums
import dagger.Module
import dagger.Provides


@Module
class NetworkModule {

    @Provides
    fun albumsRepo(
        api: ILoadAlbums,
        networkStatus: INetworkStatus,
    ): IRepoAlbums = RepoAlbums(api, networkStatus)
}