package com.diegoferreiracaetano.data.di

import com.diegoferreiracaetano.data.mock.GithubFakeApi
import com.diegoferreiracaetano.data.remote.github.api.GithubApi
import org.koin.dsl.module.module


val restMockModule = module {

    single(override = true) {
        val api: GithubApi = GithubFakeApi()
        return@single api
    }
}
