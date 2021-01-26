package com.nick_sib.testtaskappcraft.mvp.view.image

import android.widget.ImageView
import com.nick_sib.testtaskappcraft.R
import com.squareup.picasso.Picasso

class PicassoImageLoader : IImageLoader {
    override fun loadInto(url: String, container: ImageView) {
        Picasso.get()
            .load(url)
            .error(R.drawable.ic_none_image)
            .placeholder(R.drawable.progressbar)
            .into(container)


    }
}