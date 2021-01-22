package com.nick_sib.testtaskappcraft.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nick_sib.testtaskappcraft.R
import com.nick_sib.testtaskappcraft.databinding.ItemAlbumBinding
import com.nick_sib.testtaskappcraft.mvp.preseter.list.AlbumItemView
import com.nick_sib.testtaskappcraft.mvp.preseter.list.IAlbumsListPresenter
import kotlinx.android.extensions.LayoutContainer

class AlbumsRVAdapter(
    private val presenter: IAlbumsListPresenter,
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
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer, AlbumItemView {

        private var binding: ItemAlbumBinding = ItemAlbumBinding.bind(containerView)

        override fun setTitle(text: String) {
            binding.tvAlbumTitle.text = text
        }

        override var pos: Int = -1
    }

}