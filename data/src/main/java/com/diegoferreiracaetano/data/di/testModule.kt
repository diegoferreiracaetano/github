package com.diegoferreiracaetano.data.di

import androidx.room.Room
import com.diegoferreiracaetano.data.local.LocalDb
import com.diegoferreiracaetano.data.remote.github.api.GithubApi
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun testModule(baseUrl: String) = module(override = true) {

    single {
        val retrofit = Retrofit.Builder()
                .client(get())
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        retrofit.create(GithubApi::class.java)
    }

    single {
        Room.databaseBuilder(get(), LocalDb::class.java, "github-test.db")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries().build()
    }
}
