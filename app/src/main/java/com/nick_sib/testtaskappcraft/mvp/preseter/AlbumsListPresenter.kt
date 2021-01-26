package com.nick_sib.testtaskappcraft.mvp.preseter

import com.nick_sib.testtaskappcraft.mvp.model.entity.AlbumData
import com.nick_sib.testtaskappcraft.mvp.preseter.list.IAlbumItemView
import com.nick_sib.testtaskappcraft.mvp.preseter.list.IDataListPresenter

class AlbumsListPresenter: IDataListPresenter<AlbumData, IAlbumItemView> {

    private var albums = listOf<AlbumData>()

    override fun setList(data: List<AlbumData>) {
        albums = data
    }

    override fun getData(pos: Int): AlbumData = albums[pos]

    override var itemClickListener: ((IAlbumItemView) -> Unit)? = null

    override fun bindView(view: IAlbumItemView) {
        val album = albums[view.pos]
        view.setTitle(album.title)
    }

    override fun getCount(): Int = albums.size
}