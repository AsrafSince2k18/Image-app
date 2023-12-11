package com.example.imageapp.ui.presentance.event

import com.example.imageapp.domain.data.UnsplashImageItem

sealed class UnsplashEvent{

    data class SearchQuery(val query:String) : UnsplashEvent()
    object SearchIconOpened: UnsplashEvent()
    object SearchIconClosed: UnsplashEvent()
}
