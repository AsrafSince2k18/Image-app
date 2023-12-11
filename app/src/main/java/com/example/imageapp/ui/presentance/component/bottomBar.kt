package com.example.imageapp.ui.presentance.component

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.imageapp.ui.download.AndroidDownloader

@Composable
fun BottomBar(
    username: String?,
    likes: String?,
    photo: String?
) {


    BottomAppBar(
        actions = {
            BottomAction(
                username = username,
                likes = likes,
                photo = photo
            )
        },
        containerColor = Color.Transparent
    )
}

@Composable
fun BottomAction(
    username: String?,
    likes: String?,
    photo: String?,
) {




    Column(modifier = Modifier.padding(4.dp)) {



        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {

            Profile(url = photo, username = username)

            Spacer(modifier = Modifier.width(8.dp))

            if (username != null) {
                Text(
                    text = username,
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.weight(2f)
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = "Likes",
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                if (likes != null) {
                    Text(
                        text = likes,
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFFFAF0E6)
                    )
                }
            }
        }
    }
}


@Composable
fun Profile(url: String?, username: String?) {

    val context = LocalContext.current

    AsyncImage(model = ImageRequest
        .Builder(LocalContext.current)
        .crossfade(300)
        .crossfade(true)
        .data(url)
        .build(), contentDescription = "profileImage",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(50.dp)
            .clip(CircleShape)
            .clickable {
                val browser = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://unsplash.com/${username}")
                )
                ContextCompat.startActivity(context, browser, null)
            })

}

@Preview
@Composable
fun Preview() {
    BottomBar(username = "Mohamed", likes = "1232", photo = "")
}