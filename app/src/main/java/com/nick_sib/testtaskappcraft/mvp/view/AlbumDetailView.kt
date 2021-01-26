package com.nick_sib.testtaskappcraft.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType


@StateStrategyType(AddToEndSingleStrategy::class)
interface AlbumDetailView: MvpView {

    fun beginProgress()
    fun endProgress()
    fun beginCache()
    fun endCache()
    fun setFavorite(value: Boolean)
    fun showError(error: Throwable)
    fun hideShack()

    fun release()

}