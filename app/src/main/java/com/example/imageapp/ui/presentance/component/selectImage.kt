package com.example.imageapp.ui.presentance.component


import android.util.Log
import android.view.Window
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.imageapp.R
import com.example.imageapp.ui.download.AndroidDownloader

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectImage(
    onBack: () -> Unit,
    name: String?,
    url : String?,
    photos : String?,
    likes : String?,
    download : String?
   ) {



    val context = LocalContext.current
    val downloader = AndroidDownloader(context)

    Scaffold(topBar = {
        androidx.compose.material3.TopAppBar(
            title = {
                    Text(
                        text = ""
                    )
            },
            navigationIcon = {
                IconButton(onClick = { onBack() }) {
                   Image(painter = painterResource(id = R.drawable.back),
                       contentDescription = "back")
                }
            },
            actions = {
                RowDetails(onClick = {
                    if (download != null) {
                        downloader.downloadFile(download)
                    }
                })
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Transparent,
                titleContentColor = Color.Gray
            )
        )
    },

        bottomBar = {
            BottomBar(
                username =name,
                likes =likes,
                photo =photos
            )
        })
    { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
          Column {
              SelectedFullImage(url =url)

          }
        }

    }
}

@Composable
private fun SelectedFullImage(url: String?) {

    Box(modifier = Modifier) {
        Log.d("url",url.toString())
        AsyncImage(
            model = ImageRequest
                .Builder(LocalContext.current)
                .data(url)
                .crossfade(300)
                .crossfade(true)
                .build(),
            contentDescription = "SelectImage",
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center,
            error = painterResource(id = R.drawable.place_holder),
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .aspectRatio(16 / 36f)
                .background(Color(0xFF1A1918))
        )

    }
}

@Composable
fun RowDetails(onClick: () -> Unit,modifier: Modifier=Modifier){

       IconButton(onClick = {
           onClick()
       }) {
           Image(painter = painterResource(id = R.drawable.donwload),
               contentDescription ="download", modifier = modifier
                   .clip(CircleShape)
                   .size(40.dp))
       }

}
