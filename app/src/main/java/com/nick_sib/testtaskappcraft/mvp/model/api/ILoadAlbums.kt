package com.nick_sib.testtaskappcraft.mvp.model.api

import com.nick_sib.testtaskappcraft.mvp.model.entity.AlbumData
import com.nick_sib.testtaskappcraft.mvp.model.entity.AlbumInfo
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ILoadAlbums {
    @GET("albums")
    fun getAllData(): Single<List<AlbumData>>

    @GET("photos")
    fun getAlbumDetailList(@Query("albumId") dataID: String): Single<List<AlbumInfo>>
}