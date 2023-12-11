package com.example.imageapp.domain.data

import androidx.room.Embedded
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerialName("links")
    @Embedded
    val links: LinksX,

    @SerialName("username")
    val username: String,

    @SerialName("profile_image")
    @Embedded
    val profileImage : ProfileImage
)