package com.nick_sib.testtaskappcraft.mvp.preseter

import com.nick_sib.testtaskappcraft.mvp.view.ActivityView
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.Screen

class MainPresenter(private val router: Router): MvpPresenter<ActivityView>() {


    fun showScreen(screen : Screen){
        router.replaceScreen(screen)
    }

}