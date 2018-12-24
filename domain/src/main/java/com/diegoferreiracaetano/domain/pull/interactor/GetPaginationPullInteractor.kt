package com.diegoferreiracaetano.domain.pull.interactor

import com.diegoferreiracaetano.domain.pull.PullRepository
import com.diegoferreiracaetano.domain.utils.InteractorFlowable
import io.reactivex.Flowable

class GetPaginationPullInteractor(private val repositoryLocal: PullRepository) : InteractorFlowable<Int, GetPaginationPullInteractor.Request>() {

    override fun create(request: Request): Flowable<Int> {
        return repositoryLocal.getTotal()
                .map { it.div(request.limit).plus(1) }
                .toFlowable()
    }

    data class Request(val limit: Int) : InteractorFlowable.Request()
}