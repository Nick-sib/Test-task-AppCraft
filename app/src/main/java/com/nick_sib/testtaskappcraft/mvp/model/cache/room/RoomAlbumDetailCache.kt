package com.nick_sib.testtaskappcraft.mvp.model.cache.room

import android.util.Log
import com.nick_sib.testtaskappcraft.mvp.model.cache.IAlbumDetailCache
import com.nick_sib.testtaskappcraft.mvp.model.entity.AlbumData
import com.nick_sib.testtaskappcraft.mvp.model.entity.AlbumInfo
import com.nick_sib.testtaskappcraft.mvp.model.entity.room.Database
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers


class RoomAlbumDetailCache (private val db: Database) : IAlbumDetailCache {
    private val ioThread = Schedulers.io()

    override fun addAlbumData(data: AlbumData, info: List<AlbumInfo>): Completable = Completable.fromAction {
        db.albumDataDao.insert(data)
        db.albumInfoDao.insert(info)
    }.subscribeOn(ioThread)

    override fun deleteAlbumData(data: AlbumData): Completable = Completable.fromAction {
        db.albumDataDao.deleteAlbumData(data)
        db.albumInfoDao.deleteAlbumInfo(data.id)
    }.subscribeOn(ioThread)


    override fun getAllAlbumData(): Single<List<AlbumData>> = Single.fromCallable {
        db.albumDataDao.getAll()
    }

    override fun checkAlbumData(dataId: Int): Single<Boolean> = Single.fromCallable {
        !db.albumDataDao.getById(dataId).isNullOrEmpty()
    }.subscribeOn(ioThread)

}