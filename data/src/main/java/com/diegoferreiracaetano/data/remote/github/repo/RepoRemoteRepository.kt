package com.diegoferreiracaetano.data.remote.github.repo

import androidx.paging.DataSource
import com.diegoferreiracaetano.data.remote.github.api.GithubApi
import com.diegoferreiracaetano.domain.repo.Repo
import com.diegoferreiracaetano.domain.repo.RepoRepository
import io.reactivex.Flowable
import io.reactivex.Single

class RepoRemoteRepository(private val api : GithubApi) : RepoRepository {

    override fun getList(page: Int): Flowable<List<Repo>> {
        return api.getListRepo(page = page).map { it.items }
                .flatMap { Flowable.fromIterable(it)}
                .map { it.convertToRepo() }
                .toList()
                .toFlowable()
    }

    override fun getList(): DataSource.Factory<Int, Repo> {
        TODO("not implemented")
    }

    override fun getTotal(): Single<Int> {
        TODO("not implemented")
    }

    override fun save(list: List<Repo>): Flowable<List<Long>> {
        TODO("not implemented")
    }
}
