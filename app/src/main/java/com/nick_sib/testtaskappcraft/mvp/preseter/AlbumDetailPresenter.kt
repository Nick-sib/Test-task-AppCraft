package com.nick_sib.testtaskappcraft.mvp.preseter

import com.nick_sib.testtaskappcraft.mvp.model.cache.IAlbumDetailCache
import com.nick_sib.testtaskappcraft.mvp.model.cache.IAlbumInfoCache
import com.nick_sib.testtaskappcraft.mvp.model.entity.AlbumData
import com.nick_sib.testtaskappcraft.mvp.model.entity.AlbumInfo
import com.nick_sib.testtaskappcraft.mvp.model.repo.IRepoAlbumsDetail
import com.nick_sib.testtaskappcraft.mvp.model.throws.ThrowableCache
import com.nick_sib.testtaskappcraft.mvp.model.throws.ThrowableConnect
import com.nick_sib.testtaskappcraft.mvp.preseter.list.IAlbumDetailItemView
import com.nick_sib.testtaskappcraft.mvp.preseter.list.IDataListPresenter
import com.nick_sib.testtaskappcraft.mvp.view.AlbumDetailView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpPresenter

class AlbumDetailPresenter(
    private val albumData: AlbumData,
    private val albumDetailRepo: IRepoAlbumsDetail,
    private val dataCache: IAlbumDetailCache?,
    ): MvpPresenter<AlbumDetailView>() {

    private val mainThread = AndroidSchedulers.mainThread()
    private var albumInfo: List<AlbumInfo> = emptyList()

    val albumsDetailListPresenter: IDataListPresenter<AlbumInfo, IAlbumDetailItemView> = AlbumsDetailListPresenter()

    var isFavorite: Boolean = false

    override fun onFirstViewAttach() {
        loadData()
        isFavorite()
    }

    private fun loadData() {
        viewState.beginProgress()
        albumDetailRepo.loadAlbumDataList(albumData.id.toString())
            .observeOn(mainThread)
            .subscribe({
                albumInfo = it
                albumsDetailListPresenter.setList(it)
                viewState.endProgress()
            }, { error ->
                if (error is ThrowableConnect) {
                    waitInternetConnection()
                } else {
                    viewState.showError(error)
                }
            })
    }

    private fun isFavorite(){
        dataCache?.run {
            checkAlbumData(albumData.id)
                .observeOn(mainThread)
                .subscribe({
                    isFavorite = it
                    viewState.setFavorite(it)
                    }, { error ->
                        if (error is ThrowableCache) {
                            viewState.showError(ThrowableCache())
                        } else {
                            viewState.showError(error)
                        }
                    })
        } ?: run {
            viewState.showError(ThrowableCache())
        }
    }

    private fun addToFavorite(){
        dataCache?.run{
            viewState.beginCache()
            addAlbumData(albumData, albumInfo)
                .observeOn(mainThread)
                .subscribe({
                        viewState.setFavorite(true)
                        viewState.beginCache()
                    }, { error ->
                        if (error is ThrowableCache) {
                            viewState.showError(ThrowableCache())
                        } else {
                            viewState.showError(error)
                        }
                    })
        } ?: run {
            viewState.showError(ThrowableCache())
        }
    }

    private fun deleteFromFavorite(){
        dataCache?.run{
            viewState.beginCache()
            deleteAlbumData(albumData)
                .observeOn(mainThread)
                .subscribe({
                    viewState.setFavorite(false)
                    viewState.beginCache()
                }, { error ->
                    if (error is ThrowableCache) {
                        viewState.showError(ThrowableCache())
                    } else {
                        viewState.showError(error)
                    }
                })
        } ?: run {
            viewState.showError(ThrowableCache())
        }
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

    fun changeToFavorite(){
        if (isFavorite)
            deleteFromFavorite()
        else
            addToFavorite()
    }
}