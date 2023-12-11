package com.example.imageapp.domain.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.imageapp.api.UnsplashApi
import com.example.imageapp.domain.data.UnsplashImageItem
import com.example.imageapp.domain.database.UnsplashDatabase
import com.example.imageapp.mediator.UnsplashMediator
import com.example.imageapp.mediator.UnsplashSearchMediator
import com.example.imageapp.utils.Utils.ITEM_PER_PAGE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@ExperimentalPagingApi
class UnsplashRepository @Inject constructor(
    private val unsplashDatabase: UnsplashDatabase,
    private val unsplashApi: UnsplashApi
) {

    fun getAllImage(): Flow<PagingData<UnsplashImageItem>>{
        val endOfPagingReached = {unsplashDatabase.unsplashImageDao().getAllImage()}
        return Pager(
             config = PagingConfig(pageSize = ITEM_PER_PAGE),
            remoteMediator = UnsplashMediator(
                unsplashApi = unsplashApi,
                unsplashDatabase = unsplashDatabase
            ),
            pagingSourceFactory = endOfPagingReached
        ).
    }


    fun getSearchImage(query:String): Flow<PagingData<UnsplashImageItem>>{
        return Pager(
            config = PagingConfig(pageSize = ITEM_PER_PAGE),
            remoteMediator = UnsplashMediator(
                unsplashApi = unsplashApi,
                unsplashDatabase = unsplashDatabase
            ),
            pagingSourceFactory ={UnsplashSearchMediator(unsplashApi,query)}

        ).flow
    }


}