package com.nick_sib.testtaskappcraft.mvp.model.cache

import com.nick_sib.testtaskappcraft.mvp.model.entity.AlbumInfo
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IAlbumInfoCache {
    fun addAlbumInfo(data: List<AlbumInfo>): Completable
    fun getAlbumInfoById(dataId: Int): Single<List<AlbumInfo>>
    fun deleteAlbumInfo(dataId: Int): Completable
}
