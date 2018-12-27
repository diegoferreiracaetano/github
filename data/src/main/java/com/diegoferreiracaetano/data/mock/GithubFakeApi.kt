package com.diegoferreiracaetano.data.mock

import com.diegoferreiracaetano.data.remote.github.api.GithubApi
import com.diegoferreiracaetano.data.remote.github.pull.PullRemoteEntity
import com.diegoferreiracaetano.data.remote.github.repo.RepoResponseRemoteEntity

import io.reactivex.Flowable

class GithubFakeApi : GithubApi {

    override fun getListRepo(q: String, stars: String, page: Int, per_page: Int): Flowable<RepoResponseRemoteEntity> {
        return Flowable.just(GithubFake.repoResponse)
    }

    override fun getPull(onwer: String, repo: String, page: Int, per_page: Int): Flowable<List<PullRemoteEntity>> {
        return Flowable.just(GithubFake.pullList)
    }
}
