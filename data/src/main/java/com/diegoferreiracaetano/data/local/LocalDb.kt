package com.diegoferreiracaetano.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.diegoferreiracaetano.data.local.converters.DateConverter
import com.diegoferreiracaetano.data.local.pull.PullDao
import com.diegoferreiracaetano.data.local.repo.RepoDao
import com.diegoferreiracaetano.domain.pull.Pull
import com.diegoferreiracaetano.domain.repo.Repo

@Database(entities = [Repo::class,
                      Pull::class],
        version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class LocalDb : RoomDatabase() {
    abstract fun repoDao(): RepoDao
    abstract fun pullDao(): PullDao
}