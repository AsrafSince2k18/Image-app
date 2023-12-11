package com.example.imageapp.domain.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Urls(

    @SerialName("full")
    val full: String,

    @SerialName("raw")
    val raw: String,

    @SerialName("regular")
    val regular: String
)