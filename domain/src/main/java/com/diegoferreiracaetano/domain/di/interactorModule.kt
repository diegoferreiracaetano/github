package com.diegoferreiracaetano.domain.di

import com.diegoferreiracaetano.domain.pull.interactor.CallbackPullInteractor
import com.diegoferreiracaetano.domain.pull.interactor.GetListPullInteractor
import com.diegoferreiracaetano.domain.pull.interactor.GetPaginationPullInteractor
import com.diegoferreiracaetano.domain.pull.interactor.SavePullInteractor
import com.diegoferreiracaetano.domain.repo.interactor.CallbackRepoInteractor
import com.diegoferreiracaetano.domain.repo.interactor.GetListRepoInteractor
import com.diegoferreiracaetano.domain.repo.interactor.GetPaginationRepoInteractor
import com.diegoferreiracaetano.domain.repo.interactor.SaveRepoInteractor
import com.diegoferreiracaetano.domain.utils.Constants.LOCAL
import com.diegoferreiracaetano.domain.utils.Constants.REMOTE
import org.koin.dsl.module.Module
import org.koin.dsl.module.module


val interactorModule: Module = module {

    single { GetListRepoInteractor(get(LOCAL)) }
    single { SaveRepoInteractor(get(LOCAL), get(REMOTE)) }
    single { GetPaginationRepoInteractor(get(LOCAL)) }
    single { CallbackRepoInteractor(get(), get()) }

    single { GetListPullInteractor(get(LOCAL)) }
    single { SavePullInteractor(get(LOCAL), get(REMOTE)) }
    single { GetPaginationPullInteractor(get(LOCAL)) }
    single { CallbackPullInteractor(get(), get()) }

}
