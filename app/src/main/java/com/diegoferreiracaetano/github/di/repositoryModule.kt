package com.diegoferreiracaetano.github.di

import com.diegoferreiracaetano.data.local.pull.PullLocalRepository
import com.diegoferreiracaetano.data.local.repo.RepoLocalRepository
import com.diegoferreiracaetano.data.remote.pull.PullRemoteRepository
import com.diegoferreiracaetano.data.remote.repo.RepoRemoteRepository
import com.diegoferreiracaetano.domain.Constants.LOCAL
import com.diegoferreiracaetano.domain.Constants.REMOTE
import com.diegoferreiracaetano.domain.pull.Pull
import com.diegoferreiracaetano.domain.pull.PullRepository
import com.diegoferreiracaetano.domain.pull.interactor.CallbackPullInteractor
import com.diegoferreiracaetano.domain.pull.interactor.GetListPullInteractor
import com.diegoferreiracaetano.domain.pull.interactor.SavePullInicialInteractor
import com.diegoferreiracaetano.domain.pull.interactor.SavePullPageInteractor
import com.diegoferreiracaetano.domain.repo.RepoRepository
import com.diegoferreiracaetano.domain.repo.interactor.CallbackRepoInteractor
import com.diegoferreiracaetano.domain.repo.interactor.GetListRepoInteractor
import com.diegoferreiracaetano.domain.repo.interactor.SaveRepoInicialInteractor
import com.diegoferreiracaetano.domain.repo.interactor.SaveRepoPageInteractor
import org.koin.dsl.module.Module
import org.koin.dsl.module.module


val repositoryModule : Module = module {

    single(REMOTE) { RepoRemoteRepository(get()) as RepoRepository}
    single(LOCAL) { RepoLocalRepository(get()) as RepoRepository}

    single(REMOTE) { PullRemoteRepository(get()) as PullRepository}
    single(LOCAL) { PullLocalRepository(get()) as PullRepository}

    single { GetListRepoInteractor(get(LOCAL))}
    single { SaveRepoInicialInteractor(get(LOCAL),get(REMOTE))}
    single { SaveRepoPageInteractor(get(LOCAL),get(REMOTE))}
    single { CallbackRepoInteractor(get(),get())}

    single { GetListPullInteractor(get(LOCAL)) }
    single { SavePullInicialInteractor(get(LOCAL),get(REMOTE))}
    single { SavePullPageInteractor(get(LOCAL),get(REMOTE))}
    single { CallbackPullInteractor(get(),get())}

}
