package mock

import com.diegoferreiracaetano.data.remote.github.api.GithubApi
import com.diegoferreiracaetano.data.remote.github.pull.PullRemoteEntity
import com.diegoferreiracaetano.data.remote.github.repo.RepoRemoteEntity
import com.diegoferreiracaetano.data.remote.github.repo.RepoResponseRemoteEntity

import io.reactivex.Flowable

class GithubFakeApi : GithubApi {

    override fun getListRepo(q: String, stars: String, page: Int, per_page: Int): Flowable<RepoResponseRemoteEntity> {

        return Flowable.just(Mocks.repoList())
                .flatMap { Flowable.fromIterable(it) }
                .map { RepoRemoteEntity.parse(it) }
                .toList()
                .map { RepoResponseRemoteEntity(it) }
                .toFlowable()
    }

    override fun getPull(onwer: String, repo: String, page: Int, per_page: Int): Flowable<List<PullRemoteEntity>> {
        return Flowable.just(Mocks.pullList())
                .flatMap { Flowable.fromIterable(it) }
                .map { PullRemoteEntity.parse(it) }
                .toList()
                .toFlowable()
    }
}
