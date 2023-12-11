package com.example.imageapp.domain.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.imageapp.api.UnsplashApi
import com.example.imageapp.domain.data.UnsplashImageItem
import com.example.imageapp.domain.database.UnsplashDatabase
import com.example.imageapp.errorHandle.Response
import com.example.imageapp.mediator.UnsplashMediator
import com.example.imageapp.mediator.UnsplashSearchMediator
import com.example.imageapp.utils.Utils.ITEM_PER_PAGE
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException

@ExperimentalPagingApi
class RepositoryImpl (private val unsplashApi: UnsplashApi,
    private val unsplashDatabase: UnsplashDatabase
) : Repository {
    override suspend fun getAllImage(): Response<Flow<PagingData<UnsplashImageItem>>> {
        return try {
            val pagingSourceFactory = {unsplashDatabase.unsplashImageDao().getAllImage()}
            val response = Pager(
                config = PagingConfig(pageSize = ITEM_PER_PAGE),
                remoteMediator = UnsplashMediator(
                    unsplashApi = unsplashApi,
                    unsplashDatabase = unsplashDatabase
                ),
                pagingSourceFactory = pagingSourceFactory
            ).flow
            Response.Success(data = response)

        }catch (e : Exception){
            Response.Error(e.message)
        }
        catch (e : HttpException){
            Response.Error(e.message)
        }
    }

    override suspend fun getAllSearchImage(query:String): Response<Flow<PagingData<UnsplashImageItem>>> {
        return try {
            val pagingSourceFactory = {UnsplashSearchMediator(unsplashApi = unsplashApi, query = query)}
            val response = Pager(
                config = PagingConfig(pageSize = ITEM_PER_PAGE),
                remoteMediator = UnsplashMediator(
                    unsplashApi = unsplashApi,
                    unsplashDatabase = unsplashDatabase
                ),
                pagingSourceFactory = pagingSourceFactory
            ).flow
            Response.Success(data = response)

        }catch (e : Exception){
            Response.Error(e.message)
        }
        catch (e : HttpException){
            Response.Error(e.message)
        }
    }
}