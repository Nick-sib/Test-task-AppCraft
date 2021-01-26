package com.nick_sib.testtaskappcraft.mvp.preseter

import com.nick_sib.testtaskappcraft.mvp.view.ActivityView
import com.nick_sib.testtaskappcraft.navigation.Screens
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.Screen

class MainPresenter(private val router: Router): MvpPresenter<ActivityView>() {


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        router.newRootScreen(Screens.NetworkScreen())
    }

    fun showScreen(screen : Screen){
        router.newRootScreen(screen)
    }

}