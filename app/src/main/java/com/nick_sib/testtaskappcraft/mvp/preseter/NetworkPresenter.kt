package com.nick_sib.testtaskappcraft.mvp.preseter

import com.nick_sib.testtaskappcraft.mvp.model.repo.IRepoAlbums
import com.nick_sib.testtaskappcraft.mvp.model.throws.ThrowableConnect
import com.nick_sib.testtaskappcraft.mvp.preseter.list.IAlbumsListPresenter
import com.nick_sib.testtaskappcraft.mvp.view.RetrofitView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpPresenter

class NetworkPresenter(
        private val albumsRepo: IRepoAlbums
    ): MvpPresenter<RetrofitView>() {

        val albumsListPresenter: IAlbumsListPresenter = AlbumsListPresenter()

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
                    albumsListPresenter.setList(it)
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