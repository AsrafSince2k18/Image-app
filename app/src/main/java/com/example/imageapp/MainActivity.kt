package com.example.imageapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import androidx.paging.ExperimentalPagingApi
import com.example.imageapp.ui.presentance.navGraph.NavGraph

import com.example.imageapp.ui.theme.ImageAppTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalPagingApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WindowCompat.setDecorFitsSystemWindows(
                window,false)
            ImageAppTheme {
               val navHostController = rememberNavController()
                NavGraph(navHostController = navHostController)
            }
        }
    }
}

