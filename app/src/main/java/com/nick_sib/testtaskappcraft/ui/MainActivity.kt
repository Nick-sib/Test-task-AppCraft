package com.nick_sib.testtaskappcraft.ui

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import com.nick_sib.testtaskappcraft.*
import com.nick_sib.testtaskappcraft.databinding.ActivityMainBinding
import com.nick_sib.testtaskappcraft.mvp.model.api.LoadAlbumsImpl
import com.nick_sib.testtaskappcraft.mvp.model.repo.RepoAlbums
import com.nick_sib.testtaskappcraft.mvp.preseter.MainPresenter
import com.nick_sib.testtaskappcraft.mvp.view.RetrofitView
import com.nick_sib.testtaskappcraft.ui.fragments.DatabaseFragment
import com.nick_sib.testtaskappcraft.ui.fragments.NetworkFragment
import com.nick_sib.testtaskappcraft.ui.fragments.ServicesFragment
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(), RetrofitView {

    private lateinit var binding: ActivityMainBinding

    private var snack: Snackbar? = null

    private val presenter: MainPresenter by moxyPresenter {
        MainPresenter(
            RepoAlbums(
                networkStatus = LoadAlbumsImpl.networkStatus(App.instance)
            )
        )
    }


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
        //TODO("Not yet implemented")
    }

    override fun progressLoading(value: Int) {
        //TODO("Not yet implemented")
    }

    override fun showError(error: Throwable) {
        showSnack("${error.message}!", R.string.snack_button_got_it)
    }

    private fun showSnack(messageText: String, buttonText: Int, onItemClick: (() -> Unit)? = null) {
        snack = Snackbar.make(binding.root, messageText, Snackbar.LENGTH_INDEFINITE)
            .setAction(buttonText) {
                onItemClick?.invoke()
            }.apply {
                show()
            }
    }

    override fun hideShack() {
        snack?.dismiss()
    }

}