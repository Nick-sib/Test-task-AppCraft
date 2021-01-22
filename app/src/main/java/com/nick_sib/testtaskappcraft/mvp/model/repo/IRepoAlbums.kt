package com.nick_sib.testtaskappcraft.mvp.model.repo

import com.nick_sib.testtaskappcraft.mvp.model.entity.AlbumsListData
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface IRepoAlbums {
    fun loadAllAlbumsList(): Single<AlbumsListData>
    fun waitInternet(): Observable<Boolean>
}