package com.nick_sib.testtaskappcraft.mvp.model.cache.room

import com.nick_sib.testtaskappcraft.mvp.model.entity.AlbumData
import com.nick_sib.testtaskappcraft.mvp.model.entity.AlbumInfo
import com.nick_sib.testtaskappcraft.mvp.model.entity.room.Database
import com.nick_sib.testtaskappcraft.mvp.model.repo.IRepoAlbums
import com.nick_sib.testtaskappcraft.mvp.model.repo.IRepoAlbumsDetail
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RoomRepoAlbumsCache(private val db: Database): IRepoAlbums, IRepoAlbumsDetail {
    private val ioThread = Schedulers.io()

    override fun loadAllAlbumsList(): Single<List<AlbumData>> = Single.fromCallable {
        db.albumDataDao.getAll()?.let {
            it
        } ?: emptyList()
    }.subscribeOn(ioThread)

    override fun loadAlbumDataList(albumID: String): Single<List<AlbumInfo>> {
        TODO("Not yet implemented")
    }

    override fun waitInternet(): Observable<Boolean> = Observable.just(
        false
    ).subscribeOn(ioThread)
}