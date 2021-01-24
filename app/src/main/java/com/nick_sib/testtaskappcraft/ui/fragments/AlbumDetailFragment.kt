package com.nick_sib.testtaskappcraft.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.google.android.material.snackbar.Snackbar
import com.nick_sib.testtaskappcraft.App
import com.nick_sib.testtaskappcraft.R
import com.nick_sib.testtaskappcraft.databinding.FragmentAlbumDetailBinding
import com.nick_sib.testtaskappcraft.mvp.model.api.LoadAlbumsImpl
import com.nick_sib.testtaskappcraft.mvp.model.repo.RepoAlbums
import com.nick_sib.testtaskappcraft.mvp.model.throws.ThrowableConnect
import com.nick_sib.testtaskappcraft.mvp.preseter.AlbumDetailPresenter
import com.nick_sib.testtaskappcraft.mvp.view.RetrofitView
import com.nick_sib.testtaskappcraft.ui.adapter.PhotosRVAdapter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class AlbumDetailFragment: MvpAppCompatFragment(), RetrofitView {

    private var binding: FragmentAlbumDetailBinding? = null
    private var snack: Snackbar? = null //TODO: Вынести show*** в родительский абстрактный класс

    private var albumId: Int =-1

    private val presenter: AlbumDetailPresenter by moxyPresenter {
        AlbumDetailPresenter(
            albumId.toString(),
            RepoAlbums(networkStatus = LoadAlbumsImpl.networkStatus(App.instance))
        )
    }

    private val adapter: PhotosRVAdapter by lazy {
        PhotosRVAdapter(presenter.albumsDetailListPresenter)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        albumId = arguments?.getInt(EXTRA_DATA) ?: -1
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentAlbumDetailBinding.inflate(inflater, container, false).let {
        binding = it
        it.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding?.run {
            rvAlbumDetail.adapter = adapter
            bLikeDislike.setOnClickListener {
                (it as ImageView).setImageDrawable(resources.getDrawable(R.drawable.ic_dislike, null))
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    companion object {
        private val EXTRA_DATA = AlbumDetailFragment::class.java.name + "EXTRA_DATA"

        fun instance(albumId: Int) = AlbumDetailFragment().apply {
            arguments = Bundle().apply {
                putInt(EXTRA_DATA, albumId)
            }
        }
    }

    override fun beginLoading() {
        //TODO("Not yet implemented")
    }

    override fun endLoading() {
        adapter.notifyDataSetChanged()
    }

    override fun progressLoading(value: Int) {
        //TODO("Not yet implemented")
    }

    override fun showError(error: Throwable) {
        if (error is ThrowableConnect) {
            showSnack(
                resources.getString(R.string.snack_message_no_internet_connection),
                R.string.snack_button_close)
            { activity?.finish() }
        } else {
            Log.d("myLOG", "showError: $error")
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