package com.nick_sib.testtaskappcraft.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.nick_sib.testtaskappcraft.R

class AlbumDetailFragment: Fragment(R.layout.fragment_album_detail) {

    companion object {
        private val EXTRA_DATA = AlbumDetailFragment::class.java.name + "EXTRA_DATA"

        fun instance(albumId: Int) = AlbumDetailFragment().apply {
            arguments = Bundle().apply {
                putInt(EXTRA_DATA, albumId)
            }
        }
    }
}