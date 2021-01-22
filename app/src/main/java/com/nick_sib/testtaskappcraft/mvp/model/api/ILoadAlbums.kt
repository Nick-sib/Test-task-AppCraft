package com.nick_sib.testtaskappcraft.mvp.model.api

import com.nick_sib.testtaskappcraft.mvp.model.entity.AlbumData
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface ILoadAlbums {
    @GET("albums")
    fun getAllData(): Single<List<AlbumData>>

}