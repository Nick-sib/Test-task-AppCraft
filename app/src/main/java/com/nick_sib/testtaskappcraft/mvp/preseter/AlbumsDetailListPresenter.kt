package com.nick_sib.testtaskappcraft.mvp.preseter

import com.nick_sib.testtaskappcraft.mvp.model.entity.AlbumInfo
import com.nick_sib.testtaskappcraft.mvp.preseter.list.IAlbumDetailItemView
import com.nick_sib.testtaskappcraft.mvp.preseter.list.IDataListPresenter

class AlbumsDetailListPresenter: IDataListPresenter<AlbumInfo, IAlbumDetailItemView> {

    private var albums = listOf<AlbumInfo>()

    override fun setList(data: List<AlbumInfo>) {
        albums = data
    }

    override fun getData(pos: Int): AlbumInfo = albums[pos]

    override var itemClickListener: ((IAlbumDetailItemView) -> Unit)? = null

    override fun bindView(view: IAlbumDetailItemView) {
        val album = albums[view.pos]
        view.loadImage(album.thumbnailUrl)
    }

    override fun getCount(): Int = albums.size
}