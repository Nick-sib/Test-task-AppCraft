package com.nick_sib.testtaskappcraft.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import com.nick_sib.testtaskappcraft.App
import com.nick_sib.testtaskappcraft.R
import com.nick_sib.testtaskappcraft.databinding.FragmentAlbumsListBinding
import com.nick_sib.testtaskappcraft.mvp.model.cache.room.RoomRepoAlbumsCache
import com.nick_sib.testtaskappcraft.mvp.model.entity.room.Database
import com.nick_sib.testtaskappcraft.mvp.model.throws.ThrowableConnect
import com.nick_sib.testtaskappcraft.mvp.preseter.NetworkAndDatabasePresenter
import com.nick_sib.testtaskappcraft.mvp.view.RetrofitView
import com.nick_sib.testtaskappcraft.ui.adapter.AlbumsRVAdapter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class DatabaseFragment: MvpAppCompatFragment(), RetrofitView {

    private var binding: FragmentAlbumsListBinding? = null
    private var snack: Snackbar? = null

//    private val presenter: NetworkAndDatabasePresenter by moxyPresenter {
//        NetworkAndDatabasePresenter(
//            RoomRepoAlbumsCache(Database.instance!!),
//        ).apply {
//            App.instance.appComponent.inject(this)
//        }
//    }
//
//    private val adapter: AlbumsRVAdapter by lazy {
//        AlbumsRVAdapter(presenter.albumsListPresenter)
//    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentAlbumsListBinding.inflate(inflater, container, false).let {
        binding = it
        it.root
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        binding?.run {
//            rvAlbum.adapter = adapter
//        }
//        super.onViewCreated(view, savedInstanceState)
//    }

    override fun beginProgress() {
        //TODO:  показать circle_progress_bar
    }

    override fun endProgress() {
//        adapter.notifyDataSetChanged()
    }

    override fun showError(error: Throwable) {
        if (error is ThrowableConnect) {
            showSnack(
                resources.getString(R.string.snack_message_no_internet_connection),
                R.string.snack_button_close)
            { activity?.finish() }
        } else {
            showSnack("${error.message}!", R.string.snack_button_got_it)
        }
    }

    private fun showSnack(messageText: String, buttonText: Int, onItemClick: (() -> Unit)? = null) {
        binding?.run {
            snack = Snackbar.make(root, messageText, Snackbar.LENGTH_INDEFINITE)
                .setAction(buttonText) {
                    onItemClick?.invoke()
                }.apply {
                    show()
                }
        }
    }

    override fun hideShack() {
        snack?.dismiss()
    }
}