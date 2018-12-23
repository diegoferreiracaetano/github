package com.diegoferreiracaetano.data.remote.repo

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

        fun parse() = Repo(id, name, description, starts, forks, owner)
}