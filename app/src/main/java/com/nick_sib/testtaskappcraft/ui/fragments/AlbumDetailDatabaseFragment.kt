package com.nick_sib.testtaskappcraft.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import com.nick_sib.testtaskappcraft.databinding.FragmentAlbumDetailBinding
import com.nick_sib.testtaskappcraft.mvp.model.entity.AlbumData
import com.nick_sib.testtaskappcraft.mvp.view.AlbumDetailView
import moxy.MvpAppCompatFragment

class AlbumDetailDatabaseFragment: MvpAppCompatFragment(), AlbumDetailView {

    private var binding: FragmentAlbumDetailBinding? = null
    private var snack: Snackbar? = null //TODO: Вынести show*** в родительский абстрактный класс

    private lateinit var album: AlbumData
    private var albumDetailSubComponent: AlbumDetailSubComponent? = null

//    private val presenter: AlbumDetailPresenter by moxyPresenter {
//        albumDetailSubComponent = App.instance.initAlbumDetailSubComponent()
//        AlbumDetailPresenter(
//            album,
//        ).apply {
//            albumDetailSubComponent?.inject(this)
//        }
//    }
//
//    private val adapter: PhotosRVAdapter by lazy {
//        PhotosRVAdapter(presenter.albumsDetailListPresenter)
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        album = arguments?.getParcelable(AlbumDetailDatabaseFragment.EXTRA_DATA) ?: AlbumData()
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
//        binding?.run {
//            rvAlbumDetail.adapter = adapter
//            bLikeDislike.setOnClickListener {
//                presenter.changeToFavorite()
//            }
//        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }


    override fun beginProgress() {
//        TODO("Not yet implemented")
    }

    override fun endProgress() {
//        TODO("Not yet implemented")
    }

    override fun beginCache() {
//        TODO("Not yet implemented")
    }

    override fun endCache() {
//        TODO("Not yet implemented")
    }

    override fun setFavorite(value: Boolean) {
//        TODO("Not yet implemented")
    }

    override fun showError(error: Throwable) {
//        TODO("Not yet implemented")
    }

    override fun hideShack() {
//        TODO("Not yet implemented")
    }

    override fun release() {
//        TODO("Not yet implemented")
    }

    companion object {
        private val EXTRA_DATA = AlbumDetailDatabaseFragment::class.java.name + "EXTRA_DATA"

        fun instance(album: AlbumData) = AlbumDetailDatabaseFragment().apply {
            arguments = Bundle().apply {
                putParcelable(EXTRA_DATA, album)
            }
        }
    }
}