package com.nick_sib.testtaskappcraft.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface RetrofitView : MvpView {
    fun beginProgress()
    fun endProgress()
    fun showError(error: Throwable)
    fun hideShack()

    fun release()
}