package com.nick_sib.testtaskappcraft.mvp.preseter

import com.nick_sib.testtaskappcraft.mvp.model.repo.IRepoAlbums
import com.nick_sib.testtaskappcraft.mvp.model.throws.ThrowableConnect
import com.nick_sib.testtaskappcraft.mvp.view.RetrofitView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpPresenter

class MainPresenter(
        private val albumsRepo: IRepoAlbums
    ): MvpPresenter<RetrofitView>() {

    private val mainThread = AndroidSchedulers.mainThread()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadData()
    }

    private fun loadData() {
        viewState.beginLoading()
        albumsRepo.loadAllAlbumsList()
            .observeOn(mainThread)
            .subscribe({
                viewState.endLoading()
            }, { error ->
                    viewState.showError(error)
            })
    }

    private fun waitInternetConnection() {
        viewState.showError(ThrowableConnect())
        albumsRepo.waitInternet()
            .observeOn(mainThread)
            .subscribe({
                if (it) {
                    viewState.hideShack()
                    loadData()
                }
            }, {
                viewState.showError(it)
            })
    }

}