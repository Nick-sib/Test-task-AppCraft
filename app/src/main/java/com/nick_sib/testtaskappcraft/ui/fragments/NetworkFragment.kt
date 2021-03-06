package com.nick_sib.testtaskappcraft.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nick_sib.testtaskappcraft.App
import com.nick_sib.testtaskappcraft.R
import com.nick_sib.testtaskappcraft.databinding.FragmentAlbumsListBinding
import com.nick_sib.testtaskappcraft.di.network.NetworkSubComponent
import com.nick_sib.testtaskappcraft.mvp.model.throws.ThrowableConnect
import com.nick_sib.testtaskappcraft.mvp.preseter.NetworkAndDatabasePresenter
import com.nick_sib.testtaskappcraft.mvp.view.RetrofitView
import com.nick_sib.testtaskappcraft.ui.adapter.AlbumsRVAdapter
import moxy.ktx.moxyPresenter

class NetworkFragment: ParentFragment(), RetrofitView {

    private var binding: FragmentAlbumsListBinding? = null

    private var networkSubComponent: NetworkSubComponent? = null

    private val presenter: NetworkAndDatabasePresenter by moxyPresenter {
        networkSubComponent = App.instance.initNetworkSubComponent()
        NetworkAndDatabasePresenter().apply {
            networkSubComponent?.inject(this)
        }
    }

    private val adapter: AlbumsRVAdapter by lazy {
        AlbumsRVAdapter(presenter.albumsListPresenter)
    }

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding?.run {
            rvAlbum.adapter = adapter
        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun beginProgress() {
        binding?.run {
            progress.visibility = View.VISIBLE
        }
    }

    override fun endProgress() {
        binding?.run {
            progress.visibility = View.GONE
        }
        adapter.notifyDataSetChanged()
    }

    override fun showError(error: Throwable) {
        if (error is ThrowableConnect) {
            showSnack(
                binding?.root,
                resources.getString(R.string.snack_message_no_internet_connection),
                R.string.snack_button_close)
            { activity?.finish() }
        } else {
            showSnack(binding?.root,"${error.message}!", R.string.snack_button_got_it)
        }
    }

    override fun hideShack() {
        snack?.dismiss()
    }

    override fun release() {
        networkSubComponent = null
        App.instance.releaseNetworkSubComponent()
    }


}