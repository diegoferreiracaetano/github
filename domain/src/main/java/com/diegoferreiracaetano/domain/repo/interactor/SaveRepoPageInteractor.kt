package com.diegoferreiracaetano.domain.repo.interactor

import com.diegoferreiracaetano.domain.InteractorFlowable
import com.diegoferreiracaetano.domain.repo.RepoRepository
import io.reactivex.Flowable

class SaveRepoPageInteractor(private val repositoryLocal: RepoRepository,
                             private val repositoryRemote: RepoRepository): InteractorFlowable<List<Long>,SaveRepoPageInteractor.Request>() {

    override fun create(request: Request): Flowable<List<Long>> {
        return repositoryLocal.getTotal()
                .map {it.div(request.limit).plus(1)}
                .toFlowable()
                .flatMap{repositoryRemote.getList(it)}
                .flatMap{repositoryLocal.save(it)}
    }


    data class Request(val limit:Int) : InteractorFlowable.Request()
}