package com.nick_sib.testtaskappcraft.mvp.model.cache.room


import com.nick_sib.testtaskappcraft.mvp.model.cache.IAlbumInfoCache
import com.nick_sib.testtaskappcraft.mvp.model.entity.AlbumInfo
import com.nick_sib.testtaskappcraft.mvp.model.entity.room.Database
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RoomAlbumInfoCache(private val db: Database) : IAlbumInfoCache {
    override fun addAlbumInfo(data: List<AlbumInfo>): Completable = Completable.fromCallable{
        db.albumInfoDao.insert(data)
    }

    override fun getAlbumInfoById(dataId: Int): Single<List<AlbumInfo>> = Single.fromCallable {
        db.albumInfoDao.getInfoById(dataId)
    }

    override fun deleteAlbumInfo(dataId: Int): Completable = Completable.fromCallable {
        db.albumInfoDao.deleteAlbumInfo(dataId)
    }.subscribeOn(Schedulers.io())
}