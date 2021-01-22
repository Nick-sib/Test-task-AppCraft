package com.nick_sib.testtaskappcraft.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nick_sib.testtaskappcraft.*
import com.nick_sib.testtaskappcraft.databinding.ActivityMainBinding
import com.nick_sib.testtaskappcraft.ui.fragments.DatabaseFragment
import com.nick_sib.testtaskappcraft.ui.fragments.NetworkFragment
import com.nick_sib.testtaskappcraft.ui.fragments.ServicesFragment
import ru.terrakok.cicerone.android.support.SupportAppNavigator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val navigatorHolder = App.instance.navigatorHolder
    private val navigator = SupportAppNavigator(this, supportFragmentManager, R.id.container)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        findViewById<BottomNavigationView>(R.id.bottom_navigation_view)?.apply {
            setOnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.bottom_network -> {
                        //App.instance.router.replaceScreen(Screens.UsersScreen())
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.container, NetworkFragment())
                            .commitAllowingStateLoss()
                    }
                    R.id.bottom_database -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.container, DatabaseFragment())
                            .commitAllowingStateLoss()
                    }
                    R.id.bottom_services -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.container, ServicesFragment())
                            .commitAllowingStateLoss()
                    }
                    else -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.container, NetworkFragment())
                            .commitAllowingStateLoss()
                    }
                }
                true
            }
            selectedItemId = R.id.bottom_network
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