package com.example.imageapp.domain.data.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.imageapp.domain.data.UnsplashImageItem

@Dao
interface UnsplashDao {

    @Upsert
    suspend fun insertImage(image : List<UnsplashImageItem>)

    @Query("SELECT * FROM image_table")
    fun getAllImage():PagingSource<Int,UnsplashImageItem>

    @Query("DELETE FROM image_table")
    suspend fun deleteAll()

}