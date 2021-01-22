package com.nick_sib.testtaskappcraft.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface RetrofitView : MvpView {
    fun beginLoading()
    fun endLoading()
    fun progressLoading(value: Int)//если загрузка будет меньше 10 секунд заменить циклический прогрессбар
    fun showError(error: Throwable)
}