package com.example.imageapp.ui.download

import android.annotation.SuppressLint
import android.app.DownloadManager
import android.content.Context
import android.os.Environment
import androidx.core.net.toUri
import com.example.imageapp.BuildConfig


class AndroidDownloader(private val context: Context) : Download {
    private val downloadManger = context.getSystemService(DownloadManager::class.java)
    @SuppressLint("SuspiciousIndentation")
    override fun downloadFile(url: String): Long {
        val request = DownloadManager.Request(url.toUri())
            .setMimeType("image/jpeg")
            .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI)
            .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE)
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setTitle("image.jpg")
            .addRequestHeader("Authorization" , BuildConfig.API_KEY)
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,"image.jpg")
        return downloadManger.enqueue(request)
    }
}