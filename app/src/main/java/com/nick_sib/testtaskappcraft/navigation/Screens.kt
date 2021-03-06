package com.nick_sib.testtaskappcraft.navigation

import com.nick_sib.testtaskappcraft.mvp.model.entity.AlbumData
import com.nick_sib.testtaskappcraft.ui.fragments.*
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {
    class NetworkScreen : SupportAppScreen() {
        override fun getFragment() = NetworkFragment()
    }

    class DatabaseScreen : SupportAppScreen() {
        override fun getFragment() = DatabaseFragment()
    }

    class ServicesScreen : SupportAppScreen() {
        override fun getFragment() = ServicesFragment()
    }
    
    class AlbumDetailNetworkScreen(private val album: AlbumData) : SupportAppScreen() {
        override fun getFragment() = AlbumDetailNetworkFragment.instance(album)
    }

    class AlbumDetailDatabaseScreen(private val album: AlbumData) : SupportAppScreen() {
        override fun getFragment() = AlbumDetailDatabaseFragment.instance(album)
    }
}