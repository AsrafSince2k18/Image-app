package com.example.imageapp.domain.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.imageapp.domain.data.UnsplashImageItem
import com.example.imageapp.domain.data.dao.UnsplashDao
import com.example.imageapp.domain.remote.RemoteKey
import com.example.imageapp.domain.remote.dao.RemoteDao

@Database(entities = [UnsplashImageItem::class,RemoteKey::class], version = 1)
abstract class UnsplashDatabase : RoomDatabase(){
    abstract fun unsplashImageDao() : UnsplashDao
    abstract fun remoteKetDao() : RemoteDao
}