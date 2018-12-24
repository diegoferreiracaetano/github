package com.diegoferreiracaetano.domain.pull.interactor

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.diegoferreiracaetano.domain.pull.Pull
import com.diegoferreiracaetano.domain.utils.Constants
import com.diegoferreiracaetano.domain.utils.NetworkState
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action
import io.reactivex.subscribers.DisposableSubscriber

class CallbackPullInteractor(private val saveInicialInteractor: SavePullInteractor,
                             private val getPaginationInteractor: GetPaginationPullInteractor) :
        PagedList.BoundaryCallback<Pull>() {

    private var disposable = CompositeDisposable()
    private var retryCompletable: Completable? = null
    private var params = MutableLiveData<Pair<String,String>>()

    val initialLoad = MutableLiveData<NetworkState>()
    val networkState = MutableLiveData<NetworkState>()

    override fun onZeroItemsLoaded() {
        params.value?.let {
            disposable.add(saveInicialInteractor.execute(SavePullInteractor.Request(it.first, it.second, 1))
                    .subscribeWith(object : DisposableSubscriber<List<Long>>() {
                        override fun onStart() {
                            super.onStart()
                            initialLoad.postValue(NetworkState.LOADING)
                        }

                        override fun onNext(t: List<Long>) {
                            if (t.isEmpty()) {
                                initialLoad.postValue(NetworkState.IS_EMPTY)
                            } else {
                                initialLoad.postValue(NetworkState.LOADED)
                            }
                        }

                        override fun onError(t: Throwable) {
                            val erro = NetworkState.error(t.message)
                            initialLoad.postValue(erro)
                            networkState.postValue(erro)
                            setRetry(Action { onZeroItemsLoaded() })
                        }

                        override fun onComplete() {
                            networkState.postValue(NetworkState.LOADED)
                        }
                    }))
        }
    }

    override fun onItemAtEndLoaded(pull: Pull) {
        params.value?.let { it ->
            disposable.add(getPaginationInteractor.execute(GetPaginationPullInteractor.Request(Constants.PAGE_SIZE))
                    .flatMap { page -> saveInicialInteractor.execute(SavePullInteractor.Request(it.first, it.second, page)) }
                    .subscribeWith(object : DisposableSubscriber<List<Long>>() {
                        override fun onStart() {
                            super.onStart()
                            networkState.postValue(NetworkState.LOADING)
                        }

                        override fun onNext(t: List<Long>) {

                        }

                        override fun onError(t: Throwable) {
                            val erro = NetworkState.error(t.message)
                            networkState.postValue(erro)
                            setRetry(Action { onItemAtEndLoaded(pull) })
                        }

                        override fun onComplete() {
                            networkState.postValue(NetworkState.LOADED)
                        }
                    }))
        }
    }

    fun retry() {
        if (retryCompletable != null) {
            disposable.add(retryCompletable!!
                    .subscribe({ }))
        }
    }

    private fun setRetry(action: Action?) {
        if (action == null) {
            this.retryCompletable = null
        } else {
            this.retryCompletable = Completable.fromAction(action)
        }
    }
    fun setParam(params: Pair<String,String>){
        this.params.value = params
    }

    fun clear(){
        disposable.clear()
    }

}