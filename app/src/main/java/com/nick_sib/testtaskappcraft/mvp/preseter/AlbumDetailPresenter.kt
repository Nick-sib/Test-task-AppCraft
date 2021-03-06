package com.nick_sib.testtaskappcraft.mvp.preseter

import com.nick_sib.testtaskappcraft.mvp.model.cache.IAlbumDetailCache
import com.nick_sib.testtaskappcraft.mvp.model.entity.AlbumData
import com.nick_sib.testtaskappcraft.mvp.model.entity.AlbumInfo
import com.nick_sib.testtaskappcraft.mvp.model.repo.IRepoAlbumsDetail
import com.nick_sib.testtaskappcraft.mvp.model.throws.ThrowableCache
import com.nick_sib.testtaskappcraft.mvp.model.throws.ThrowableConnect
import com.nick_sib.testtaskappcraft.mvp.preseter.list.IAlbumDetailItemView
import com.nick_sib.testtaskappcraft.mvp.preseter.list.IDataListPresenter
import com.nick_sib.testtaskappcraft.mvp.view.AlbumDetailView
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import javax.inject.Inject

class AlbumDetailPresenter(
    private val albumData: AlbumData,
): MvpPresenter<AlbumDetailView>() {

    @Inject
    lateinit var albumDetailRepo: IRepoAlbumsDetail
    @Inject
    lateinit var dataCache: IAlbumDetailCache
    @Inject
    lateinit var mainThread: Scheduler

    private var albumInfo: List<AlbumInfo> = emptyList()

    val albumsDetailListPresenter: IDataListPresenter<AlbumInfo, IAlbumDetailItemView> = AlbumsDetailListPresenter()

    private var isFavorite: Boolean = false

    override fun onFirstViewAttach() {
        loadData()
        isFavorite()
    }

    override fun onDestroy() {
        viewState.release()
        super.onDestroy()
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
        dataCache.run {
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
        dataCache.run{
            addAlbumData(albumData, albumInfo)
                .observeOn(mainThread)
                .subscribe({
                        viewState.setFavorite(true)
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
        dataCache.run{
            deleteAlbumData(albumData)
                .observeOn(mainThread)
                .subscribe({
                    viewState.setFavorite(false)
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