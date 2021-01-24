package com.nick_sib.testtaskappcraft.mvp.model.cache.room

import com.nick_sib.testtaskappcraft.mvp.model.cache.IAlbumDataCache
import com.nick_sib.testtaskappcraft.mvp.model.entity.AlbumData
import com.nick_sib.testtaskappcraft.mvp.model.entity.room.Database
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers


class RoomAlbumDataCache (private val db: Database) : IAlbumDataCache {
    private val ioThread = Schedulers.io()
    override fun addAlbumData(data: AlbumData): Completable = Completable.fromCallable {
        db.albumDataDao.insert(data)
    }.subscribeOn(ioThread)

    override fun getAllAlbumData(): Single<List<AlbumData>> = Single.fromCallable {
        db.albumDataDao.getAll()
    }

    override fun deleteAlbumData(data: AlbumData): Completable = Completable.fromCallable {
        db.albumDataDao.deleteAlbumData(data)
    }.subscribeOn(ioThread)

    override fun checkAlbumData(dataId: Int): Single<Boolean> = Single.fromCallable {
        !db.albumDataDao.getById(dataId).isNullOrEmpty()
    }.subscribeOn(ioThread)

}