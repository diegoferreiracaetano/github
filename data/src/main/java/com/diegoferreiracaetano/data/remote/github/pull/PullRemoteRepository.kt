package com.diegoferreiracaetano.data.remote.github.pull

import androidx.paging.DataSource
import com.diegoferreiracaetano.data.remote.github.api.GithubApi
import com.diegoferreiracaetano.domain.pull.Pull
import com.diegoferreiracaetano.domain.pull.PullRepository
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single

class PullRemoteRepository(private val api : GithubApi) : PullRepository {

    override fun getList(owner:String,repo:String,page : Int): Flowable<List<Pull>> {
        return api.getPull(owner,repo,page)
                .flatMap{Flowable.fromIterable(it)}
                .map { it.convertToPull() }
                .flatMapMaybe { Maybe.just(it.copy(ownerName = owner, repoName = repo)) }
                .toList()
                .toFlowable()
    }

    override fun getList(owner: String, repo: String): DataSource.Factory<Int, Pull> {
        TODO("not implemented")
    }

    override fun getTotal(): Single<Int> {
        TODO("not implemented")
    }

    override fun save(list: List<Pull>): Flowable<List<Long>> {
        TODO("not implemented")
    }
}
