package com.example.imageapp.domain.search

import com.example.imageapp.domain.data.UnsplashImageItem
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchResult(

    @SerialName("results")
    val resul : List<UnsplashImageItem>

)
