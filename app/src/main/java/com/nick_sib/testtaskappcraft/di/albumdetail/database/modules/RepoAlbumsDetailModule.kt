package com.nick_sib.testtaskappcraft.di.albumdetail.database.modules

import com.nick_sib.testtaskappcraft.di.albumdetail.database.AlbumDetailDatabaseScope
import com.nick_sib.testtaskappcraft.mvp.model.cache.room.RoomRepoAlbumsCache
import com.nick_sib.testtaskappcraft.mvp.model.entity.room.Database
import com.nick_sib.testtaskappcraft.mvp.model.repo.IRepoAlbumsDetail
import dagger.Module
import dagger.Provides

@Module
class RepoAlbumsDetailModule {
    @AlbumDetailDatabaseScope
    @Provides
    fun albumsRepo(
        database: Database
    ): IRepoAlbumsDetail = RoomRepoAlbumsCache(database)

}