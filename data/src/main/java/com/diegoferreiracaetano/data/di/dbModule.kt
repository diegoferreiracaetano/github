package com.diegoferreiracaetano.data.di

import androidx.room.Room
import com.diegoferreiracaetano.data.local.LocalDb
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

val dbModule: Module = module {

    single {
        Room.databaseBuilder(get(), LocalDb::class.java, "github.db")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries().build()
    }

    single { get<LocalDb>().repoDao() }
    single { get<LocalDb>().pullDao() }
}
