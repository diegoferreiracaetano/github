package com.diegoferreiracaetano.data.local.pull

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.diegoferreiracaetano.domain.owner.Owner
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

@Entity(tableName = "pull")
data class PullLocalEntity(
                @PrimaryKey
                val id:Long,
                val title:String,
                val date:Date,
                val body:String?,
                val url:String,
                @Embedded(prefix = "user_")
                var owner: Owner,
                @ColumnInfo(name = "owner_name")
                var ownerName: String,
                @ColumnInfo(name = "repo_name")
                var repoName: String): Serializable {
    constructor():this(0,"",Date(),"","", Owner(), "","")
}