package com.example.imageapp.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.imageapp.api.UnsplashApi
import com.example.imageapp.domain.data.UnsplashImageItem
import com.example.imageapp.domain.database.UnsplashDatabase
import com.example.imageapp.domain.remote.RemoteKey
import com.example.imageapp.utils.Utils.ITEM_PER_PAGE
import retrofit2.HttpException

@ExperimentalPagingApi
class UnsplashMediator(
    private val unsplashApi: UnsplashApi,
    private val unsplashDatabase: UnsplashDatabase
) : RemoteMediator<Int, UnsplashImageItem>() {

    private val unsplashImageDao = unsplashDatabase.unsplashImageDao()
    private val remoteKeyDao = unsplashDatabase.remoteKetDao()


    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, UnsplashImageItem>
    ): MediatorResult {
        return try {
            val currentPage = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKey = getReferesh(state)
                    remoteKey?.nextPage?.minus(1) ?: 1
                }

                LoadType.PREPEND -> {
                    val remoteKey = getPreviosPage(state)
                    val previosPage = remoteKey?.previewsPage
                        ?: return MediatorResult.Success(remoteKey!=null)
                    previosPage

                }

                LoadType.APPEND -> {
                    val remoteKey = getNextPage(state)
                    val nextPage = remoteKey?.nextPage
                        ?: return MediatorResult.Success(remoteKey!=null)
                    nextPage
                }
            }

            val response = unsplashApi.getAllImage(
                page = currentPage,
                perPage = ITEM_PER_PAGE
            )

            val endOfPagingReached = response.isEmpty()
            val previousPage = if(currentPage == 1) null else currentPage-1
            val nextPage = if(endOfPagingReached) null else currentPage+1

            unsplashDatabase.withTransaction {
                if(loadType == LoadType.REFRESH){
                    unsplashImageDao.deleteAll()
                    remoteKeyDao.deleteAll()
                }
                val key = response.map {
                    RemoteKey(
                        id = it.id,
                        previewsPage = previousPage,
                        nextPage = nextPage
                    )
                }
                unsplashImageDao.insertImage(image = response)
                remoteKeyDao.insertKey(key=key)
            }

            MediatorResult.Success(endOfPagingReached)

        } catch (e: Exception) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }


    private suspend fun getReferesh(state: PagingState<Int, UnsplashImageItem>): RemoteKey? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let {
                remoteKeyDao.getAllRemoteKey(it)
            }
        }
    }

    private suspend fun getNextPage(state: PagingState<Int, UnsplashImageItem>): RemoteKey? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()?.let {
            remoteKeyDao.getAllRemoteKey(id = it.id)
        }
    }

    private suspend fun getPreviosPage(state: PagingState<Int, UnsplashImageItem>): RemoteKey? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()?.let {
            remoteKeyDao.getAllRemoteKey(id = it.id)
        }
    }
}