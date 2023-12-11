package com.example.imageapp.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.room.Room
import com.example.imageapp.api.UnsplashApi
import com.example.imageapp.domain.database.UnsplashDatabase
import com.example.imageapp.domain.repository.Repository
import com.example.imageapp.domain.repository.RepositoryImpl
import com.example.imageapp.utils.Utils
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@ExperimentalPagingApi
@Module
@InstallIn(SingletonComponent::class)
object AppModule {


        @Provides
        @Singleton
        fun provideDatabase(@ApplicationContext context: Context):UnsplashDatabase{
            return Room
                .databaseBuilder(context,UnsplashDatabase::class.java,"unsplashItem")
                .build()
        }


        @Provides
        @Singleton
        fun provideRepository(unsplashApi: UnsplashApi,
                              unsplashDatabase: UnsplashDatabase):Repository{
            return RepositoryImpl(unsplashApi = unsplashApi, unsplashDatabase = unsplashDatabase)
        }

        @Provides
        @Singleton
        fun okHttpClient(): OkHttpClient {
            return OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .build()
        }

        @Provides
        @Singleton
        fun retrofitProvide(okHttpClient: OkHttpClient) : Retrofit {
            val contentType = "application/json".toMediaType()
            val json = Json {
                ignoreUnknownKeys = true
            }

            return Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(json.asConverterFactory(contentType))
                .baseUrl(Utils.BASE_URL)
                .build()

        }

        @Provides
        @Singleton
        fun provideUnsplashApi(retrofit: Retrofit): UnsplashApi {
            return retrofit.create(UnsplashApi::class.java)
        }


}