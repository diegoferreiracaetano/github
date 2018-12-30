package com.diegoferreiracaetano.data.remote.github.api

import com.diegoferreiracaetano.data.remote.github.pull.PullRemoteEntity
import com.diegoferreiracaetano.data.remote.github.repo.RepoResponseRemoteEntity
import com.diegoferreiracaetano.domain.utils.Constants
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi {

    @GET("search/repositories")
    fun getListRepo(
        @Query("q") q: String = "language:Java",
        @Query("sort") stars: String = "stars",
        @Query("page") page: Int,
        @Query("per_page") per_page: Int = Constants.PAGE_SIZE
    ): Flowable<RepoResponseRemoteEntity>

    @GET("repos/{owner}/{repo}/pulls")
    fun getPull(
        @Path("owner") onwer: String,
        @Path("repo") repo: String,
        @Query("page") page: Int,
        @Query("per_page") per_page: Int = Constants.PAGE_SIZE
    ): Flowable<List<PullRemoteEntity>>
}
