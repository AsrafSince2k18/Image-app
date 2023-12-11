package com.example.imageapp.domain.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProfileImage(

    @SerialName("small")
    val small : String,

    @SerialName("medium")
    val medium : String
)
