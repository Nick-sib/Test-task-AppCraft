package com.nick_sib.testtaskappcraft.mvp.preseter.list


interface IDataListPresenter<T, V: IItemView>: IListPresenter<V> {//AlbumItemView
    fun setList(data: List<T>)
    fun getData(pos: Int): T
}