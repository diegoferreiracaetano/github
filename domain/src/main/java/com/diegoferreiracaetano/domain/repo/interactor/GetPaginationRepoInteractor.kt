package com.diegoferreiracaetano.domain.repo.interactor

import com.diegoferreiracaetano.domain.repo.RepoRepository
import com.diegoferreiracaetano.domain.utils.InteractorFlowable
import io.reactivex.Flowable

class GetPaginationRepoInteractor(private val repositoryLocal: RepoRepository) : InteractorFlowable<Int, GetPaginationRepoInteractor.Request>() {

    override fun create(request: Request): Flowable<Int> {
        return repositoryLocal.getTotal()
                .map { it.div(request.limit).plus(1) }
                .toFlowable()
    }

    data class Request(val limit: Int) : InteractorFlowable.Request()
}