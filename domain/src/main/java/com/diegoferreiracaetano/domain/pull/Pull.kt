package com.diegoferreiracaetano.domain.pull

import com.diegoferreiracaetano.domain.owner.Owner
import java.io.Serializable
import java.util.*

data class Pull(
        val id: Long,
        val title: String,
        val date: Date,
        val body: String?,
        val url: String,
        var owner: Owner,
        var ownerName: String?,
        var repoName: String) : Serializable {
    constructor():this(0,"",Date(),"","", Owner(), "","")
}