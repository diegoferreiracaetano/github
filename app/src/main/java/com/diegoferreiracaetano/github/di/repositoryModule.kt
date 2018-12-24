package com.diegoferreiracaetano.github.di

import com.diegoferreiracaetano.data.local.pull.PullLocalRepository
import com.diegoferreiracaetano.data.local.repo.RepoLocalRepository
import com.diegoferreiracaetano.data.remote.github.pull.PullRemoteRepository
import com.diegoferreiracaetano.data.remote.github.repo.RepoRemoteRepository
import com.diegoferreiracaetano.domain.pull.PullRepository
import com.diegoferreiracaetano.domain.pull.interactor.CallbackPullInteractor
import com.diegoferreiracaetano.domain.pull.interactor.GetListPullInteractor
import com.diegoferreiracaetano.domain.pull.interactor.GetPaginationPullInteractor
import com.diegoferreiracaetano.domain.pull.interactor.SavePullInteractor
import com.diegoferreiracaetano.domain.repo.RepoRepository
import com.diegoferreiracaetano.domain.repo.interactor.CallbackRepoInteractor
import com.diegoferreiracaetano.domain.repo.interactor.GetListRepoInteractor
import com.diegoferreiracaetano.domain.repo.interactor.GetPaginationRepoInteractor
import com.diegoferreiracaetano.domain.repo.interactor.SaveRepoInteractor
import com.diegoferreiracaetano.domain.utils.Constants.LOCAL
import com.diegoferreiracaetano.domain.utils.Constants.REMOTE
import org.koin.dsl.module.Module
import org.koin.dsl.module.module


val repositoryModule : Module = module {

    single(REMOTE) { RepoRemoteRepository(get()) as RepoRepository}
    single(LOCAL) { RepoLocalRepository(get()) as RepoRepository}

    single(REMOTE) { PullRemoteRepository(get()) as PullRepository}
    single(LOCAL) { PullLocalRepository(get()) as PullRepository}

    single { GetListRepoInteractor(get(LOCAL))}
    single { SaveRepoInteractor(get(LOCAL), get(REMOTE)) }
    single { GetPaginationRepoInteractor(get(LOCAL)) }
    single { CallbackRepoInteractor(get(),get())}

    single { GetListPullInteractor(get(LOCAL)) }
    single { SavePullInteractor(get(LOCAL), get(REMOTE)) }
    single { GetPaginationPullInteractor(get(LOCAL)) }
    single { CallbackPullInteractor(get(),get())}

}
