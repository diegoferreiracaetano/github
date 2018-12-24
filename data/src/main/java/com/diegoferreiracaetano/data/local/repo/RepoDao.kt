package com.diegoferreiracaetano.data.local.repo

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Single

@Dao
interface RepoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(entityLocals: List<RepoLocalEntity>): List<Long>

    @Query("SELECT * FROM repo ORDER BY repo.starts DESC")
    fun getAll(): DataSource.Factory<Int, RepoLocalEntity>

    @Query("SELECT COUNT(*) FROM repo")
    fun getTotal() : Single<Int>
}
