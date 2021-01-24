package com.nick_sib.testtaskappcraft.mvp.model.cache

import com.nick_sib.testtaskappcraft.mvp.model.entity.AlbumData
import com.nick_sib.testtaskappcraft.mvp.model.entity.AlbumInfo
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IAlbumDetailCache {
    fun addAlbumData(data: AlbumData, info: List<AlbumInfo>): Completable
    //fun addAlbumData(data: AlbumData): Completable
    fun getAllAlbumData(): Single<List<AlbumData>>
    fun deleteAlbumData(data: AlbumData): Completable
    fun checkAlbumData(dataId: Int): Single<Boolean>
}
