package com.example.imageapp.ui.presentance.component


import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.imageapp.R


@Composable
fun ImageLoader(
    url:String,
    modifier: Modifier=Modifier
) {

    AsyncImage(model = ImageRequest
        .Builder(LocalContext.current)
        .data(url)
        .crossfade(300)
        .crossfade(true)
        .build(),
        contentDescription = "image",
        contentScale = ContentScale.Crop,
        placeholder = painterResource(id = R.drawable.place_holder),
        error = painterResource(id = R.drawable.place_holder),
        alignment = Alignment.Center,
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))



    )
}