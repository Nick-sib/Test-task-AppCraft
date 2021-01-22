package com.nick_sib.testtaskappcraft

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
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
                        true
                    }
                    R.id.bottom_database -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.container, DatabaseFragment())
                            .commitAllowingStateLoss()
                        true
                    }
                    R.id.bottom_services -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.container, ServicesFragment())
                            .commitAllowingStateLoss()
                        true
                    }
                    else -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.container, NetworkFragment())
                            .commitAllowingStateLoss()
                        true
                    }
                }
            }
            selectedItemId = R.id.bottom_network

        }

    }
}