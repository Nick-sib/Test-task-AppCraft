package com.nick_sib.testtaskappcraft.di.modules

import com.nick_sib.testtaskappcraft.App
import com.nick_sib.testtaskappcraft.mvp.model.api.ILoadAlbums
import com.nick_sib.testtaskappcraft.mvp.model.netstate.INetworkStatus
import com.nick_sib.testtaskappcraft.ui.network.AndroidNetworkStatus
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApiModule {

    private val baseUrl: String = "https://jsonplaceholder.typicode.com/"

    @Provides
    fun api(): ILoadAlbums = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ILoadAlbums::class.java)

    @Singleton
    @Provides
    fun networkStatus(app: App): INetworkStatus = AndroidNetworkStatus(app)
}