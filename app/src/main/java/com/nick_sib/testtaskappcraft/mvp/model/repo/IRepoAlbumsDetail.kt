package com.nick_sib.testtaskappcraft.mvp.model.repo


import com.nick_sib.testtaskappcraft.mvp.model.entity.AlbumInfo
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface IRepoAlbumsDetail {
    fun loadAlbumDataList(albumID: String): Single<List<AlbumInfo>>
    fun waitInternet(): Observable<Boolean>
}