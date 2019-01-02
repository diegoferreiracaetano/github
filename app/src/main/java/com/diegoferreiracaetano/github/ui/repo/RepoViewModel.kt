package com.diegoferreiracaetano.github.ui.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.diegoferreiracaetano.domain.repo.Repo
import com.diegoferreiracaetano.domain.repo.interactor.CallbackRepoInteractor
import com.diegoferreiracaetano.domain.repo.interactor.GetListRepoInteractor
import com.diegoferreiracaetano.domain.utils.Constants

class RepoViewModel(getRepoInteractor: GetListRepoInteractor, val callback: CallbackRepoInteractor)
    : ViewModel() {

    private val itemSelected = MutableLiveData<Repo>()
    val result: LiveData<PagedList<Repo>>

    init {

        val config = PagedList.Config.Builder()
                .setPageSize(Constants.PAGE_SIZE)
                .setInitialLoadSizeHint(Constants.PAGE_SIZE)
                .setEnablePlaceholders(true)
                .build()

        result = LivePagedListBuilder<Int, Repo>(getRepoInteractor.execute(), config)
                .setBoundaryCallback(callback)
                .build()
    }

    fun setItem(repo: Repo) {
        itemSelected.value = repo
    }

    fun getItem(): LiveData<Repo> {
        return itemSelected
    }

    fun refresh() {
        callback.onZeroItemsLoaded()
    }

    fun retry() {
        callback.retry()
    }

    val networkState = Transformations.switchMap(result, { callback.networkState })
    val initialLoad = Transformations.switchMap(result, { callback.initialLoad })

    override fun onCleared() {
        super.onCleared()
        callback.clear()
    }
}
