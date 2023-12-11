package com.example.imageapp.ui.presentance.states

import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import com.example.imageapp.domain.data.UnsplashImageItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class UnsplashStates(

    val searchBarVisible: Boolean = false,
    val searchQuery: String ="",
    val errorShow: String? = null,
    val isLoading: Boolean = false,
    val imageShow: Flow<PagingData<UnsplashImageItem>> = emptyFlow(),
    val image : LazyPagingItems<UnsplashImageItem> ?=null


)
