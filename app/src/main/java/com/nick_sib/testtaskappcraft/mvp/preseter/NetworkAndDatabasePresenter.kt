package com.nick_sib.testtaskappcraft.mvp.preseter


import com.nick_sib.testtaskappcraft.mvp.model.entity.AlbumData
import com.nick_sib.testtaskappcraft.mvp.model.repo.IRepoAlbums
import com.nick_sib.testtaskappcraft.mvp.model.throws.ThrowableConnect
import com.nick_sib.testtaskappcraft.mvp.preseter.list.IAlbumItemView
import com.nick_sib.testtaskappcraft.mvp.preseter.list.IDataListPresenter
import com.nick_sib.testtaskappcraft.mvp.view.RetrofitView
import com.nick_sib.testtaskappcraft.navigation.Screens
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class NetworkAndDatabasePresenter: MvpPresenter<RetrofitView>() {

    @Inject
    lateinit var router: Router
    @Inject
    lateinit var albumsRepo: IRepoAlbums
    @Inject
    lateinit var mainThread: Scheduler

    val albumsListPresenter: IDataListPresenter<AlbumData, IAlbumItemView> = AlbumsListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadData()

        albumsListPresenter.itemClickListener = { itemView ->
            val album = albumsListPresenter.getData(itemView.pos)
            //влюбых подходах хоть сеть хоть база если нет интернета гузим данные из базы
            albumsRepo.waitInternet()
                .observeOn(mainThread)
                .subscribe {
                    if (it)
                        router.navigateTo(Screens.AlbumDetailNetworkScreen(album))
                    else
                        router.navigateTo(Screens.AlbumDetailDatabaseScreen(album))
                }


        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewState.release()
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