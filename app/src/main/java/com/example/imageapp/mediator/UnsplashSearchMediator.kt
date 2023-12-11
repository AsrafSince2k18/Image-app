package com.example.imageapp.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.imageapp.api.UnsplashApi
import com.example.imageapp.domain.data.UnsplashImageItem
import com.example.imageapp.utils.Utils.ITEM_PER_PAGE
import retrofit2.HttpException
import java.io.IOException

@ExperimentalPagingApi
class UnsplashSearchMediator(
    private val unsplashApi: UnsplashApi,
    private val query : String
) : PagingSource<Int,UnsplashImageItem>(){
    override fun getRefreshKey(state: PagingState<Int, UnsplashImageItem>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UnsplashImageItem> {
        val currentPage = params.key ?: 1
        return try {
            val response = unsplashApi.getAllSearchImage(
                query = query,
                page = currentPage,
                perPage = ITEM_PER_PAGE
            )
            val endOfPagingReached = response.resul.isEmpty()
            if(response.resul.isNotEmpty()){
                LoadResult.Page(
                    data = response.resul,
                    prevKey = if(currentPage==1) null else currentPage-1,
                    nextKey = if(endOfPagingReached)null else currentPage+1
                )
            }else{
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }

        }catch (e : IOException){
            LoadResult.Error(e)
        }
        catch (e : HttpException){
            LoadResult.Error(e)
        }
    }
}