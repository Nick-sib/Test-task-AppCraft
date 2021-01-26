package com.nick_sib.testtaskappcraft.mvp.model.api

import com.nick_sib.testtaskappcraft.App
import com.nick_sib.testtaskappcraft.mvp.model.netstate.INetworkStatus
import com.nick_sib.testtaskappcraft.ui.network.AndroidNetworkStatus
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object LoadAlbumsImpl {
//до дагера оставим это так
    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    fun getAllData(): ILoadAlbums = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ILoadAlbums::class.java)


    fun networkStatus(app: App): INetworkStatus = AndroidNetworkStatus(app)
}