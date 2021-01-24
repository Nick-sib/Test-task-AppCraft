package com.nick_sib.testtaskappcraft.mvp.model.cache

import com.nick_sib.testtaskappcraft.mvp.model.entity.AlbumData
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IAlbumDataCache {//TODO: interface перегасыщен в будующем разделить на 2
    fun addAlbumData(data: AlbumData): Completable
    fun getAllAlbumData(): Single<List<AlbumData>>
    fun deleteAlbumData(data: AlbumData): Completable
    fun checkAlbumData(dataId: Int): Single<Boolean>
}
