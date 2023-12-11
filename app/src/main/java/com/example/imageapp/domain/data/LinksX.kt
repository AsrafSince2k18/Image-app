package com.example.imageapp.domain.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LinksX(

    @SerialName("html")
    val html: String,
    @SerialName("photos")
    val photos: String,
)