package com.example.newsapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.Repository
import com.example.newsapp.model.NewsResult
import com.example.newsapp.util.Constants
import com.example.newsapp.util.Constants.Companion.API_KEY
import com.example.newsapp.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    application: Application,
    private val repository: Repository
): AndroidViewModel(application){

    var newsResponse: MutableLiveData<NetworkResult<NewsResult>> = MutableLiveData()

    fun getQueries(): HashMap<String, String> {
        val queries: HashMap<String, String> = HashMap()

        queries["country"] = "us"
        queries["apiKey"] = API_KEY
        queries["pageSize"] = "100"
        return queries
    }

    fun getNews(queries: Map<String, String>) = viewModelScope.launch {
        getNewsResultCall(queries)
    }

    private suspend fun getNewsResultCall(queries: Map<String, String>) {
        newsResponse.value = NetworkResult.Loading()

        try {
            val response = repository.remote.getNews(queries)
            newsResponse.value = NetworkResult.Success(response.body()!!)
        } catch(e: Exception) {
            newsResponse.value = NetworkResult.Error("News Not Found.")
        }
    }

}