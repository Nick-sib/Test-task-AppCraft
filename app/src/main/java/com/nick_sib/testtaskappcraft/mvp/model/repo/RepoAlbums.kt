package com.nick_sib.testtaskappcraft.mvp.model.repo

import com.nick_sib.testtaskappcraft.mvp.model.api.ILoadAlbums
import com.nick_sib.testtaskappcraft.mvp.model.entity.AlbumData
import com.nick_sib.testtaskappcraft.mvp.model.entity.AlbumInfo
import com.nick_sib.testtaskappcraft.mvp.model.netstate.INetworkStatus
import com.nick_sib.testtaskappcraft.mvp.model.throws.ThrowableConnect
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RepoAlbums(
    private val api: ILoadAlbums,
    private val networkStatus: INetworkStatus,
): IRepoAlbums, IRepoAlbumsDetail {
    override fun loadAllAlbumsList(): Single<List<AlbumData>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            api.getAllData()
        } else {
            throw ThrowableConnect()
        }
    }.subscribeOn(Schedulers.io())

    override fun loadAlbumDataList(albumID: String): Single<List<AlbumInfo>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            api.getAlbumDetailList(albumID)
        } else {
            throw ThrowableConnect()
        }
    }.subscribeOn(Schedulers.io())


    override fun waitInternet(): Observable<Boolean> =
        networkStatus.isOnline().takeUntil{ isOnline ->
            isOnline == true
        }.subscribeOn(Schedulers.io())
}