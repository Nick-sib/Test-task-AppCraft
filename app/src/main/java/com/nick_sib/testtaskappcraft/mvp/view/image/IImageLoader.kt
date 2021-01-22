package com.nick_sib.testtaskappcraft.mvp.view.image

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}