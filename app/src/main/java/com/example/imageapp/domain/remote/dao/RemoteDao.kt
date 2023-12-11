package com.example.imageapp.domain.remote.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.imageapp.domain.remote.RemoteKey

@Dao
interface RemoteDao {

    @Upsert
    suspend fun insertKey(key : List<RemoteKey>)

    @Query("SELECT * FROM remote_key WHERE id =:id")
    suspend fun getAllRemoteKey(id:String):RemoteKey

    @Query("DELETE FROM remote_key")
    suspend fun deleteAll()

}