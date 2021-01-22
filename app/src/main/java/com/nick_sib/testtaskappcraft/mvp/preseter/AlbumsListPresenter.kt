package com.nick_sib.testtaskappcraft.mvp.preseter

import com.nick_sib.testtaskappcraft.mvp.model.entity.AlbumData
import com.nick_sib.testtaskappcraft.mvp.preseter.list.AlbumItemView
import com.nick_sib.testtaskappcraft.mvp.preseter.list.IAlbumsListPresenter

class AlbumsListPresenter: IAlbumsListPresenter {

    var albums = listOf<AlbumData>()

    override fun setList(data: List<AlbumData>) {
        albums = data
    }

    override fun getData(pos: Int): AlbumData = albums[pos]

    override var itemClickListener: ((AlbumItemView) -> Unit)? = null

    override fun bindView(view: AlbumItemView) {
        val album = albums[view.pos]
        view.setTitle(album.title)
    }

    override fun getCount(): Int = albums.size
}