package com.nick_sib.testtaskappcraft.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import com.nick_sib.testtaskappcraft.App
import com.nick_sib.testtaskappcraft.R
import com.nick_sib.testtaskappcraft.databinding.FragmentAlbumDetailBinding
import com.nick_sib.testtaskappcraft.di.albumdetail.AlbumDetailNetworkSubComponent
import com.nick_sib.testtaskappcraft.mvp.model.entity.AlbumData
import com.nick_sib.testtaskappcraft.mvp.model.throws.ThrowableCache
import com.nick_sib.testtaskappcraft.mvp.model.throws.ThrowableConnect
import com.nick_sib.testtaskappcraft.mvp.preseter.AlbumDetailPresenter
import com.nick_sib.testtaskappcraft.mvp.view.AlbumDetailView
import com.nick_sib.testtaskappcraft.ui.adapter.PhotosRVAdapter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class AlbumDetailNetworkFragment: MvpAppCompatFragment(), AlbumDetailView {

    private var binding: FragmentAlbumDetailBinding? = null
    private var snack: Snackbar? = null //TODO: Вынести show*** в родительский абстрактный класс

    private lateinit var album: AlbumData
    private var albumDetailNetworkSubComponent: AlbumDetailNetworkSubComponent? = null

    private val presenter: AlbumDetailPresenter by moxyPresenter {
        albumDetailNetworkSubComponent = App.instance.initAlbumDetailNetworkSubComponent()
        AlbumDetailPresenter(
            album,
        ).apply {
            albumDetailNetworkSubComponent?.inject(this)
        }
    }

    private val adapter: PhotosRVAdapter by lazy {
        PhotosRVAdapter(presenter.albumsDetailListPresenter)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        album = arguments?.getParcelable(EXTRA_DATA) ?: AlbumData()
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
                presenter.changeToFavorite()
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    override fun beginProgress() {
        binding?.bLikeDislike?.visibility = View.GONE
    }

    override fun endProgress() {
        adapter.notifyDataSetChanged()
        binding?.bLikeDislike?.visibility = View.VISIBLE
    }

    override fun beginCache() {
        //TODO("Not yet implemented")
    }

    override fun endCache() {
        //TODO("Not yet implemented")
    }

    override fun showError(error: Throwable) {
        when (error) {
            is ThrowableConnect -> {
                    showSnack(
                        resources.getString(R.string.snack_message_no_internet_connection),
                        R.string.snack_button_close
                    )
                    { activity?.finish() }
            }
            is ThrowableCache -> {
                //можно спрятать кнопку Favorite если еще не добалено но если добавлено не прятать
                    showSnack(
                        resources.getString(R.string.snack_message_no_internet_connection),
                        R.string.snack_button_got_it
                    )
            }
            else -> {
                Log.d("myLOG", "showError: $error")
                showSnack("${error.message}!", R.string.snack_button_got_it)
            }
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

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun setFavorite(value: Boolean) {
        binding?.also {
            it.bLikeDislike.setImageDrawable(
                resources.getDrawable(if (value) R.drawable.ic_dislike else R.drawable.ic_like,
                null))
        }
    }

    override fun release() {
        albumDetailNetworkSubComponent = null
        App.instance.releaseAlbumDetailSubComponent()
    }

    companion object {
        private val EXTRA_DATA = AlbumDetailNetworkFragment::class.java.name + "EXTRA_DATA"

        fun instance(album: AlbumData) = AlbumDetailNetworkFragment().apply {
            arguments = Bundle().apply {
                putParcelable(EXTRA_DATA, album)
            }
        }
    }
}