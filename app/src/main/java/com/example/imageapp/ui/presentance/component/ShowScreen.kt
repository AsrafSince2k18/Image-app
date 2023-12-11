package com.example.imageapp.ui.presentance.component

import android.util.Log
import android.widget.Toast
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.imageapp.domain.data.UnsplashImageItem
import com.example.imageapp.ui.presentance.event.UnsplashEvent
import com.example.imageapp.ui.presentance.states.UnsplashStates
import com.example.imageapp.ui.presentance.viewmodel.UnsplashViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(
    ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class,
    ExperimentalComposeUiApi::class
)
@ExperimentalPagingApi
@Composable
fun ListShow(
    unsplashViewModel: UnsplashViewModel = hiltViewModel(),
    navHostController: NavHostController,
    states: UnsplashStates,
    event: (UnsplashEvent) -> Unit
) {

    val focus = remember {
        FocusRequester()
    }
    val keyboardManager = LocalFocusManager.current

    val keyboardControl = LocalSoftwareKeyboardController.current

    val item = unsplashViewModel.state.imageShow.collectAsLazyPagingItems()

    val coroutine = rememberCoroutineScope()

    val context = LocalContext.current


    LaunchedEffect(key1 = Unit) {
        if (states.searchQuery.isNotEmpty()) {
            event(UnsplashEvent.SearchQuery(query = states.searchQuery))
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        androidx.compose.material3.Scaffold(
            modifier = Modifier,
            topBar = {
                TopAppBar(
                    name = "Image",
                    onSearch = {
                        states.searchQuery
                    }
                )
            }
        )
        { padding ->
            Box(
                modifier = Modifier
                    .padding(padding)
            ) {
                LaunchedEffect(key1 = item.loadState) {
                    if (item.loadState.refresh is LoadState.Error) {
                        Toast.makeText(
                            context,
                            "Error " + (item.loadState.refresh as LoadState.Error).error.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                ShowList(item, navHostController)


            }
        }
    }
}

@Composable
@OptIn(ExperimentalFoundationApi::class)
private fun ShowList(
    item: LazyPagingItems<UnsplashImageItem>,
    navHostController: NavHostController,
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(3),
            contentPadding = PaddingValues(4.dp),
            verticalItemSpacing = 8.dp,
            horizontalArrangement = Arrangement.spacedBy(8.dp)

        ) {
            items(item.itemCount) { index ->
                item[index]?.let { it ->
                    ImageDisplay(
                        unsplashImageItem = it,
                        navHostController = navHostController
                    )
                }
            }
        }
    }
}
