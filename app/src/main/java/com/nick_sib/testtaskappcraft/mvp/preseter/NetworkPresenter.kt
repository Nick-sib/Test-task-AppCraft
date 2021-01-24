package com.nick_sib.testtaskappcraft.mvp.preseter

import com.nick_sib.testtaskappcraft.mvp.model.entity.AlbumData
import com.nick_sib.testtaskappcraft.mvp.model.repo.IRepoAlbums
import com.nick_sib.testtaskappcraft.mvp.model.throws.ThrowableConnect
import com.nick_sib.testtaskappcraft.mvp.preseter.list.IAlbumItemView
import com.nick_sib.testtaskappcraft.mvp.preseter.list.IDataListPresenter
import com.nick_sib.testtaskappcraft.mvp.view.RetrofitView
import com.nick_sib.testtaskappcraft.navigation.Screens
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class NetworkPresenter(
        private val albumsRepo: IRepoAlbums,
        private val router: Router,
    ): MvpPresenter<RetrofitView>() {

        val albumsListPresenter: IDataListPresenter<AlbumData, IAlbumItemView> = AlbumsListPresenter()

        private val mainThread = AndroidSchedulers.mainThread()

        override fun onFirstViewAttach() {
            super.onFirstViewAttach()
            loadData()

            albumsListPresenter.itemClickListener = { itemView ->
                val album = albumsListPresenter.getData(itemView.pos)
                router.navigateTo(Screens.AlbumDetailScreen(album))
            }
        }

        private fun loadData() {
            viewState.beginProgress()
            albumsRepo.loadAllAlbumsList()
                .observeOn(mainThread)
                .subscribe({
                    albumsListPresenter.setList(it)
                    viewState.endProgress()
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