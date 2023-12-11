package com.example.imageapp.domain.remote

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.imageapp.utils.Utils.REMOTE

@Entity(tableName = REMOTE)
data class RemoteKey(

    @PrimaryKey(autoGenerate = false)
    val id : String,

    val nextPage : Int?,

    val previewsPage : Int?

)
