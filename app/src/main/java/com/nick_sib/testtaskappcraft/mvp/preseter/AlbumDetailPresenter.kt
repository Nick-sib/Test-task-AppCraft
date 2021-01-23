package com.nick_sib.testtaskappcraft.mvp.preseter

import com.nick_sib.testtaskappcraft.mvp.model.repo.IRepoAlbumsDetail
import com.nick_sib.testtaskappcraft.mvp.model.throws.ThrowableConnect
import com.nick_sib.testtaskappcraft.mvp.view.RetrofitView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpPresenter

class AlbumDetailPresenter(
        private val albumId: String,
        private val albumDetailRepo: IRepoAlbumsDetail,
    ): MvpPresenter<RetrofitView>() {

    private val mainThread = AndroidSchedulers.mainThread()

    override fun onFirstViewAttach() {

        loadData()
    }

    private fun loadData() {
        viewState.beginLoading()
        albumDetailRepo.loadAlbumDataList(albumId)
            .observeOn(mainThread)
            .subscribe({
                //albumsListPresenter.setList(it)
                viewState.endLoading()
            }, { error ->
                if (error is ThrowableConnect) {
                    waitInternetConnection()
                } else {
                    viewState.showError(error)
                }
            })
    }

    private fun waitInternetConnection() {
        viewState.showError(ThrowableConnect())
        albumDetailRepo.waitInternet()
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