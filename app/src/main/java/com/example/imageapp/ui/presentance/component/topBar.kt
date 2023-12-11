package com.example.imageapp.ui.presentance.component

import android.annotation.SuppressLint
import androidx.annotation.ColorLong
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.example.imageapp.domain.data.UnsplashImageItem

@SuppressLint("InvalidColorHexValue")
@ExperimentalMaterial3Api
@Composable
fun TopAppBar(
    name : String,
    onSearch : () -> Unit
) {

    androidx.compose.material3.TopAppBar(title = {
        Text(text = name,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold)
    },
        actions = {
                  IconButton(onClick = {onSearch()}) {
                      Icon(imageVector = Icons.Default.Search,
                          contentDescription = "search")
                  }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFFF0F0F0),
            actionIconContentColor = Color.Black

        )

    )

}