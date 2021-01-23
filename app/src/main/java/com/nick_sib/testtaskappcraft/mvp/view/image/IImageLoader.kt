package com.nick_sib.testtaskappcraft.mvp.view.image

import android.widget.ImageView

interface IImageLoader {
    fun loadInto(url: String, container: ImageView)
}