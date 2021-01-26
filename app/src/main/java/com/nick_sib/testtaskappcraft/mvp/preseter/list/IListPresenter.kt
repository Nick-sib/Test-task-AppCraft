package com.nick_sib.testtaskappcraft.mvp.preseter.list

interface IListPresenter<V : IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}