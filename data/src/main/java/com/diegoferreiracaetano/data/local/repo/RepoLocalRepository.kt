package com.diegoferreiracaetano.data.local.repo

import androidx.paging.DataSource
import com.diegoferreiracaetano.domain.repo.Repo
import com.diegoferreiracaetano.domain.repo.RepoRepository
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single

class RepoLocalRepository(private var dao: RepoDao) : RepoRepository {

    override fun getList(page: Int): Flowable<List<Repo>> {
        return Flowable.empty()
    }

    override fun getList(): DataSource.Factory<Int, Repo> {
        return dao.getAll().map { it.convertToRepo() }
    }

    override fun save(list: List<Repo>): Flowable<List<Long>> {
        return Flowable.just(list)
                .flatMap { Flowable.fromIterable(it) }
                .flatMapMaybe { Maybe.just(RepoLocalEntity.parse(it)) }
                .toList()
                .map { dao.save(it) }
                .toFlowable()
    }

    override fun getTotal(): Single<Int> {
       return dao.getTotal()
    }

}
