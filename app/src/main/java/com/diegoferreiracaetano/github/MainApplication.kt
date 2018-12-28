package com.diegoferreiracaetano.github

import android.app.Application
import com.diegoferreiracaetano.data.di.dbModule
import com.diegoferreiracaetano.data.di.repositoryModule
import com.diegoferreiracaetano.data.di.restModule
import com.diegoferreiracaetano.domain.di.interactorModule
import com.diegoferreiracaetano.github.di.viewModelModule
import org.koin.android.ext.android.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(restModule, repositoryModule, interactorModule, viewModelModule, dbModule))
    }
}