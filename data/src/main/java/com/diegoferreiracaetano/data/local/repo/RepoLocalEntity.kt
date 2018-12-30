package com.diegoferreiracaetano.data.local.repo

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.diegoferreiracaetano.domain.owner.Owner
import com.diegoferreiracaetano.domain.repo.Repo
import java.io.Serializable

@Entity(tableName = "repo",
        indices = arrayOf(Index(value = ["starts"])))
data class RepoLocalEntity(
    @PrimaryKey
    var id: Long,
    var name: String,
    var description: String?,
    var starts: Int,
    var forks: Int,
    @Embedded(prefix = "owner_")
    var owner: Owner
) : Serializable {
        constructor(): this(0, "", "", 0, 0, Owner())

    fun convertToRepo() = Repo(id, name, description, starts, forks, owner)

    companion object {
        fun parse(repo: Repo) = RepoLocalEntity(
                repo.id,
                repo.name,
                repo.description,
                repo.starts,
                repo.forks,
                repo.owner
        )
    }
}