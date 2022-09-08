package com.example.newsapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.Repository
import com.example.newsapp.model.NewsResult
import com.example.newsapp.util.Constants.Companion.INPUT_NEWS_API_KEY
import com.example.newsapp.util.Constants.Companion.INPUT_NEWS_COUNTRY
import com.example.newsapp.util.Constants.Companion.INPUT_NEWS_PAGE_SIZE
import com.example.newsapp.util.Constants.Companion.NEWS_APIKEY
import com.example.newsapp.util.Constants.Companion.NEWS_CATEGORY
import com.example.newsapp.util.Constants.Companion.NEWS_COUNTRY
import com.example.newsapp.util.Constants.Companion.NEWS_PAGE_SIZE
import com.example.newsapp.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    application: Application,
    private val repository: Repository
): AndroidViewModel(application){

    var newsResponse: MutableLiveData<NetworkResult<NewsResult>> = MutableLiveData()

    fun getQueries(): HashMap<String, String> {
        val queries: HashMap<String, String> = HashMap()

        queries[NEWS_COUNTRY] = INPUT_NEWS_COUNTRY
        queries[NEWS_APIKEY] = INPUT_NEWS_API_KEY
        queries[NEWS_PAGE_SIZE] = INPUT_NEWS_PAGE_SIZE

        return queries
    }

    fun getCategoryQueries(category: String): HashMap<String, String> {
        val queries: HashMap<String, String> = HashMap()

        queries[NEWS_COUNTRY] = INPUT_NEWS_COUNTRY
        queries[NEWS_CATEGORY] = category
        queries[NEWS_APIKEY] = INPUT_NEWS_API_KEY
        queries[NEWS_PAGE_SIZE] = INPUT_NEWS_PAGE_SIZE

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