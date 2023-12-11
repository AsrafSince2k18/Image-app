package com.example.imageapp.ui.presentance.component

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.imageapp.domain.data.UnsplashImageItem
import kotlin.random.Random

@SuppressLint("SuspiciousIndentation")
@Composable
fun ImageDisplay(
   unsplashImageItem: UnsplashImageItem,
   navHostController: NavHostController
) {

       Surface(onClick = {
           navHostController.navigate("select_image?id=${unsplashImageItem.user.username}" +
                   "&url=${unsplashImageItem.urls.regular}" +
                   "&small=${unsplashImageItem.user.profileImage.small}" +
                   "&likes=${unsplashImageItem.likes}" +
                   "&download=${unsplashImageItem.links.download}")
                         },
           shadowElevation = 4.dp,
           shape = MaterialTheme.shapes.medium,
           modifier = Modifier
               .fillMaxSize()) {
           Box(modifier = Modifier
               .height(250.dp)
               .width(20.dp)) {
               ImageLoader(url = unsplashImageItem.urls.regular)
           }
   }

}