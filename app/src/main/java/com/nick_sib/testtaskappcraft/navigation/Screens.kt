package com.nick_sib.testtaskappcraft.navigation

import com.nick_sib.testtaskappcraft.mvp.model.entity.AlbumData
import com.nick_sib.testtaskappcraft.ui.fragments.AlbumDetailFragment
import com.nick_sib.testtaskappcraft.ui.fragments.DatabaseFragment
import com.nick_sib.testtaskappcraft.ui.fragments.NetworkFragment
import com.nick_sib.testtaskappcraft.ui.fragments.ServicesFragment
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
    
    class AlbumDetailScreen(private val album: AlbumData) : SupportAppScreen() {
        override fun getFragment() = AlbumDetailFragment.instance(album)
    }
}