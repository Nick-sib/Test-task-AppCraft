package com.nick_sib.testtaskappcraft.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nick_sib.testtaskappcraft.R
import com.nick_sib.testtaskappcraft.databinding.ItemAlbumDetailBinding
import com.nick_sib.testtaskappcraft.mvp.model.entity.AlbumInfo
import com.nick_sib.testtaskappcraft.mvp.preseter.list.IAlbumDetailItemView
import com.nick_sib.testtaskappcraft.mvp.preseter.list.IDataListPresenter
import com.nick_sib.testtaskappcraft.mvp.view.image.PicassoImageLoader
import com.nick_sib.testtaskappcraft.mvp.view.image.IImageLoader
import kotlinx.android.extensions.LayoutContainer

class PhotosRVAdapter(
        private val presenter: IDataListPresenter<AlbumInfo, IAlbumDetailItemView>,
        private val imageLoader: IImageLoader = PicassoImageLoader()
) : RecyclerView.Adapter<PhotosRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_album_detail, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pos = position
        presenter.bindView(holder)
    }

    override fun getItemCount(): Int = presenter.getCount()

    inner class ViewHolder(
            override val containerView: View
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer, IAlbumDetailItemView {

        private var binding: ItemAlbumDetailBinding = ItemAlbumDetailBinding.bind(containerView)

        override fun loadImage(url: String) {
            imageLoader.loadInto(url, binding.ivDetailImage)
        }

        override var pos: Int = -1
    }
}