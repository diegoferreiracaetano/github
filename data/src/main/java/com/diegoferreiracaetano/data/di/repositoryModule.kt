package com.diegoferreiracaetano.data.di

import com.diegoferreiracaetano.data.local.pull.PullLocalRepository
import com.diegoferreiracaetano.data.local.repo.RepoLocalRepository
import com.diegoferreiracaetano.data.remote.github.pull.PullRemoteRepository
import com.diegoferreiracaetano.data.remote.github.repo.RepoRemoteRepository
import com.diegoferreiracaetano.domain.pull.PullRepository
import com.diegoferreiracaetano.domain.repo.RepoRepository
import com.diegoferreiracaetano.domain.utils.Constants.LOCAL
import com.diegoferreiracaetano.domain.utils.Constants.REMOTE
import org.koin.dsl.module.module

val repositoryModule = module {

    single(REMOTE) { RepoRemoteRepository(get()) as RepoRepository }
    single(LOCAL) { RepoLocalRepository(get()) as RepoRepository }

    single(REMOTE) { PullRemoteRepository(get()) as PullRepository }
    single(LOCAL) { PullLocalRepository(get()) as PullRepository }
}
