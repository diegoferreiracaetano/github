package com.diegoferreiracaetano.data.remote.github.pull

import com.diegoferreiracaetano.domain.owner.Owner
import com.diegoferreiracaetano.domain.pull.Pull
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.Date

data class PullRemoteEntity(
    val id: Long,
    val title: String,
    @SerializedName("created_at")
    val date: Date,
    val body: String?,
    @SerializedName("html_url")
    val url: String,
    @SerializedName("user")
    var owner: Owner,
    var ownerName: String?,
    var repoName: String
) : Serializable {

    fun convertToPull() = Pull(id, title, date, body, url, owner, "", "")

    companion object {
        fun parse(pull: Pull) = PullRemoteEntity(
                pull.id,
                pull.title,
                pull.date,
                pull.body,
                pull.url,
                pull.owner,
                pull.ownerName,
                pull.repoName
        )
    }
}