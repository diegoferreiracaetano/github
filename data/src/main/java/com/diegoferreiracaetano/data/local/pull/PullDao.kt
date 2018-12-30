package com.diegoferreiracaetano.data.local.pull

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Single

@Dao
interface PullDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(entityLocals: List<PullLocalEntity>): List<Long>

    @Query("SELECT * FROM pull WHERE pull.owner_name = :ownerName AND pull.repo_name = :repoName ORDER BY pull.id DESC")
    fun getAll(ownerName: String, repoName: String): DataSource.Factory<Int, PullLocalEntity>

    @Query("SELECT COUNT(*) FROM pull")
    fun getTotal(): Single<Int>
}
