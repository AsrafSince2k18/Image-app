package com.example.imageapp.domain.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Links(
    @SerialName("download")
    val download: String,
    @SerialName("self")
    val self: String
)