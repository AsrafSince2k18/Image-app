package com.example.imageapp.ui.presentance.navGraph

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.paging.ExperimentalPagingApi
import com.example.imageapp.ui.presentance.component.ListShow
import com.example.imageapp.ui.presentance.component.SelectImage
import com.example.imageapp.ui.presentance.viewmodel.UnsplashViewModel

@OptIn(ExperimentalPagingApi::class)
@Composable
fun NavGraph(navHostController: NavHostController) {

    val id = "id"
    val url = "url"
    val small = "small"
    val likes = "likes"
    val download = "download"

    val viewModel : UnsplashViewModel = hiltViewModel()
    NavHost(navController = navHostController,
        startDestination = "home_screen"){
        composable("home_screen"){
            ListShow(navHostController = navHostController,
                states = viewModel.state,
                event = viewModel::onEvent)
        }
        composable("select_image?$id={$id}" +
                "&$url={$url}" +
                "&$small={$small}" +
                "&$likes={$likes}" +
                "&$download={$download}",
            arguments = listOf(navArgument(name = id){
                type= NavType.StringType

                navArgument(name = url){
                    type= NavType.StringType
                }
                navArgument(name = small){
                    type= NavType.StringType
                }
            }
            )

        ){

                SelectImage(
                    onBack = { navHostController.popBackStack() },
                    name = it.arguments?.getString(id),
                    url = it.arguments?.getString(url),
                    photos = it.arguments?.getString(small),
                    likes = it.arguments?.getString(likes),
                    download = it.arguments?.getString(download)
                )
        }


    }

}