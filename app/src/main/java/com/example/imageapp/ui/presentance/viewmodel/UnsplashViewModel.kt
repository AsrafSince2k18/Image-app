package com.example.imageapp.ui.presentance.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import com.example.imageapp.domain.data.UnsplashImageItem
import com.example.imageapp.domain.repository.Repository
import com.example.imageapp.errorHandle.Response
import com.example.imageapp.ui.presentance.event.UnsplashEvent
import com.example.imageapp.ui.presentance.states.UnsplashStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@ExperimentalPagingApi
@HiltViewModel
class UnsplashViewModel @Inject constructor(
    private val repository: Repository
):ViewModel(){

   var state by mutableStateOf(UnsplashStates())

    init {
        getAllImage()
    }
    private var job : Job?=null

    fun onEvent(event : UnsplashEvent){
       when(event){
           UnsplashEvent.SearchIconClosed -> {
               state = state.copy(searchBarVisible = false)
           }
           UnsplashEvent.SearchIconOpened -> {
               state = state.copy(searchBarVisible = true)
           }
           is UnsplashEvent.SearchQuery -> {
               state = state.copy(searchQuery = event.query)
               job?.cancel()
               job=viewModelScope.launch {
                   delay(1000)
                   searchImage(query = event.query)
               }
           }

       }
    }


    fun searchImage(query:String){
        if(query.isEmpty()){
            Log.d("myTag","query return $query")
            return
        }
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            state = when(val result = repository.getAllSearchImage(query = query)
                ){
                is Response.Error -> {
                    state.copy(errorShow = result.message ?:"",
                        isLoading = false,
                        imageShow = emptyFlow()
                    )
                }
                is Response.Success ->{

                    state.copy(
                        errorShow = null,
                        isLoading = false,
                        imageShow = result.data ?: emptyFlow()
                    )
                }
            }

        }
    }

   fun getAllImage(){
       viewModelScope.launch {
           state = state.copy(isLoading = true)
           state = when(val result = repository.getAllImage()){
              is Response.Error -> {
                  state.copy(errorShow = result.message ?:"",
                      isLoading = false,
                      imageShow = emptyFlow()
                  )
              }
              is Response.Success ->{
                  state.copy(
                      errorShow = null,
                      isLoading = false,
                      imageShow = result.data ?: emptyFlow()
                  )
              }
          }
       }
   }




}