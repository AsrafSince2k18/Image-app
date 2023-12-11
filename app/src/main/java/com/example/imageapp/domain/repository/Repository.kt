package com.example.imageapp.domain.repository

import androidx.paging.PagingData
import com.example.imageapp.domain.data.UnsplashImageItem
import com.example.imageapp.errorHandle.Response
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun getAllImage() : Response<Flow<PagingData<UnsplashImageItem>>>

    suspend fun getAllSearchImage(query:String) : Response<Flow<PagingData<UnsplashImageItem>>>



}