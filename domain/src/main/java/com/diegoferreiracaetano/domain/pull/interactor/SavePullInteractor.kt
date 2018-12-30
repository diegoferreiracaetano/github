package com.diegoferreiracaetano.domain.pull.interactor

import com.diegoferreiracaetano.domain.pull.PullRepository
import com.diegoferreiracaetano.domain.utils.InteractorFlowable
import io.reactivex.Flowable

class SavePullInteractor(
    private val repositoryLocal: PullRepository,
    private val repositoryRemote: PullRepository
) : InteractorFlowable<List<Long>, SavePullInteractor.Request>() {

    override fun create(request: Request): Flowable<List<Long>> {
        return repositoryRemote.getList(request.owner, request.repo, request.page)
                .flatMap { repositoryLocal.save(it) }
    }

    data class Request(val owner: String, val repo: String, val page: Int) : InteractorFlowable.Request()
}