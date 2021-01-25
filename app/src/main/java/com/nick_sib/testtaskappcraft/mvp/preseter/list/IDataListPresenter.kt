package com.nick_sib.testtaskappcraft.mvp.preseter.list


interface IDataListPresenter<T, V: IItemView>: IListPresenter<V> {
    fun setList(data: List<T>)
    fun getData(pos: Int): T
}