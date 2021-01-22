package com.nick_sib.testtaskappcraft

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nick_sib.testtaskappcraft.mvp.preseter.MainPresenter
import com.nick_sib.testtaskappcraft.mvp.view.RetrofitView
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(), RetrofitView {

    private val presenter: MainPresenter = MainPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<BottomNavigationView>(R.id.bottom_navigation_view)?.apply {
            setOnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.bottom_network -> {
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

    override fun beginLoading() {

    }

    override fun endLoading() {
        TODO("Not yet implemented")
    }

    override fun progressLoading(value: Int) {
        TODO("Not yet implemented")
    }

    override fun showError(error: Throwable) {
        TODO("Not yet implemented")
    }


}