package com.diegoferreiracaetano.data.local.pull

import androidx.paging.DataSource
import com.diegoferreiracaetano.domain.pull.Pull
import com.diegoferreiracaetano.domain.pull.PullRepository
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single

class PullLocalRepository(private var dao: PullDao) : PullRepository {

    override fun getList(owner:String,repo:String,page : Int): Flowable<List<Pull>> {
        return Flowable.empty()
    }

    override fun getList(owner:String,repo:String): DataSource.Factory<Int, Pull> {
        return dao.getAll(owner, repo).map { it.convertToPull() }
    }

    override fun save(list: List<Pull>): Flowable<List<Long>> {
        return Flowable.just(list)
                .flatMap { Flowable.fromIterable(it) }
                .flatMapMaybe { Maybe.just(PullLocalEntity.parse(it)) }
                .toList()
                .map { dao.save(it) }
                .toFlowable()
    }

    override fun getTotal(): Single<Int> {
        return dao.getTotal()
    }
}
