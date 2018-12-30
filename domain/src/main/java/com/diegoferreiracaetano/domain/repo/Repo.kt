package com.diegoferreiracaetano.domain.repo

import com.diegoferreiracaetano.domain.owner.Owner

data class Repo(
    var id: Long,
    var name: String,
    var description: String?,
    var starts: Int,
    var forks: Int,
    var owner: Owner
)
