package com.nick_sib.testtaskappcraft.ui.fragments

import android.view.View
import com.google.android.material.snackbar.Snackbar
import moxy.MvpAppCompatFragment

abstract class ParentFragment: MvpAppCompatFragment() {

    var snack: Snackbar? = null

    fun showSnack(
        view: View?,
        messageText: String,
        buttonText: Int,
        onItemClick: (() -> Unit)? = null
    ) {
        view?.run {
            snack = Snackbar.make(this, messageText, Snackbar.LENGTH_INDEFINITE)
                .setAction(buttonText) {
                    onItemClick?.invoke()
                }.apply {
                    show()
                }
        }
    }
}