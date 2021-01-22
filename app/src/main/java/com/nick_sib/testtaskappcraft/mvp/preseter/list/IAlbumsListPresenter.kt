package com.nick_sib.testtaskappcraft.mvp.preseter.list

import com.nick_sib.testtaskappcraft.mvp.model.entity.AlbumData

interface IAlbumsListPresenter: IListPresenter<AlbumItemView> {
    fun setList(data: List<AlbumData>)
    fun getData(pos: Int): AlbumData
}