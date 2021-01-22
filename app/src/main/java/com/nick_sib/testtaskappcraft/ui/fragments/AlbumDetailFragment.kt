package com.nick_sib.testtaskappcraft.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment

class AlbumDetailFragment: Fragment() {

    companion object {
        private val EXTRA_DATA = AlbumDetailFragment::class.java.name + "EXTRA_DATA"

        fun instance(albumId: Int) = AlbumDetailFragment().apply {
            arguments = Bundle().apply {
                putInt(EXTRA_DATA, albumId)
            }
        }
    }
}