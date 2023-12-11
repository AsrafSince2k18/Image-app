package com.example.imageapp.domain.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.imageapp.utils.Utils.IMAGE
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = IMAGE)
data class UnsplashImageItem(

    @PrimaryKey(autoGenerate = false)
    val id: String,

    @Embedded
    @SerialName("urls")
    val urls: Urls,

    @Embedded
    @SerialName("links")
    val links: Links,

    val likes: Int,

    @Embedded
    @SerialName("user")
    val user: User
)