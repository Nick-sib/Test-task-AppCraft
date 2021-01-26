package com.nick_sib.testtaskappcraft.ui

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nick_sib.testtaskappcraft.*
import com.nick_sib.testtaskappcraft.databinding.ActivityMainBinding
import com.nick_sib.testtaskappcraft.mvp.preseter.MainPresenter
import com.nick_sib.testtaskappcraft.mvp.view.ActivityView
import com.nick_sib.testtaskappcraft.navigation.Screens
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.terrakok.cicerone.android.support.SupportAppNavigator

class MainActivity : MvpAppCompatActivity(), ActivityView {

    private lateinit var binding: ActivityMainBinding

    private val navigatorHolder = App.instance.navigatorHolder
    private val navigator = SupportAppNavigator(this, supportFragmentManager, R.id.container)


    private val presenter: MainPresenter by moxyPresenter {
        MainPresenter(App.instance.router)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationView.apply {
            setOnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.bottom_network -> {
                        presenter.showScreen(Screens.NetworkScreen())
                    }
                    R.id.bottom_database -> {
                        presenter.showScreen(Screens.DatabaseScreen())
                    }
                    R.id.bottom_services -> {
                        presenter.showScreen(Screens.ServicesScreen())
                    }
                    else -> {
                        presenter.showScreen(Screens.NetworkScreen())
                    }
                }
                true
            }
            //selectedItemId = R.id.bottom_network
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }


}