package com.diegoferreiracaetano.domain.repo.interactor

import com.diegoferreiracaetano.domain.InteractorFlowable
import com.diegoferreiracaetano.domain.repo.RepoRepository
import io.reactivex.Flowable

class SaveRepoInicialInteractor(private val repositoryLocal: RepoRepository,
                                private val repositoryRemote: RepoRepository): InteractorFlowable<List<Long>,SaveRepoInicialInteractor.Request>() {

    override fun create(request: Request): Flowable<List<Long>> {
        return repositoryRemote.getList(request.page)
                .flatMap { repositoryLocal.save(it) }
    }


    data class Request(val page:Int) : InteractorFlowable.Request()
}