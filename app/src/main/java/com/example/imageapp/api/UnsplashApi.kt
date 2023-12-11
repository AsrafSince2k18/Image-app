package com.example.imageapp.api

import com.example.imageapp.BuildConfig
import com.example.imageapp.domain.data.UnsplashImageItem
import com.example.imageapp.domain.search.SearchResult
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface UnsplashApi {

    @Headers("Authorization: Client-ID ${BuildConfig.API_KEY}")
    @GET("/photos")
    suspend fun getAllImage(
        @Query("page") page : Int,
        @Query("per_page") perPage : Int
    ):List<UnsplashImageItem>

    @Headers("Authorization: Client-ID ${BuildConfig.API_KEY}")
    @GET("/search/photos")
    suspend fun getAllSearchImage(
        @Query("query") query : String,
        @Query("page") page : Int,
        @Query("per_page") perPage : Int
    ):SearchResult
}