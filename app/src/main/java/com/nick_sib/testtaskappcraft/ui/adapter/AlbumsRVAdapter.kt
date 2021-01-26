package com.nick_sib.testtaskappcraft.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nick_sib.testtaskappcraft.R
import com.nick_sib.testtaskappcraft.databinding.ItemAlbumBinding
import com.nick_sib.testtaskappcraft.mvp.model.entity.AlbumData
import com.nick_sib.testtaskappcraft.mvp.preseter.list.IAlbumItemView
import com.nick_sib.testtaskappcraft.mvp.preseter.list.IDataListPresenter
import kotlinx.android.extensions.LayoutContainer

class AlbumsRVAdapter(
        private val presenter: IDataListPresenter<AlbumData, IAlbumItemView>,
) : RecyclerView.Adapter<AlbumsRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context)
            .inflate(R.layout.item_album, parent, false))

    override fun getItemCount() = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pos = position
        holder.containerView.setOnClickListener {
            presenter.itemClickListener?.invoke(holder)
        }
        presenter.bindView(holder)
    }

    inner class ViewHolder(
        override val containerView: View
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer, IAlbumItemView {

        private var binding: ItemAlbumBinding = ItemAlbumBinding.bind(containerView)

        override fun setTitle(text: String) {
            binding.tvAlbumTitle.text = text
        }

        override var pos: Int = -1
    }

}