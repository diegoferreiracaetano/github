package com.diegoferreiracaetano.data.remote.github.repo

import androidx.room.Embedded
import com.diegoferreiracaetano.domain.owner.Owner
import com.diegoferreiracaetano.domain.repo.Repo
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RepoRemoteEntity (
        var id:Long,
        var name:String,
        var description:String?,
        @SerializedName("stargazers_count")
        var starts:Int,
        @SerializedName("forks_count")
        var forks:Int,
        @Embedded(prefix = "owner_")
        var owner: Owner) :Serializable{

    fun convertToRepo() = Repo(id, name, description, starts, forks, owner)

    companion object {
        fun parse(repo: Repo) = RepoRemoteEntity(repo.id, repo.name, repo.description, repo.starts, repo.forks, repo.owner)
    }
}