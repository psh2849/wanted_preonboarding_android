package com.example.newsapp.data

import com.example.newsapp.data.network.NewsRemoteApi
import com.example.newsapp.model.NewsResult
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val newsRemoteApi: NewsRemoteApi
) {
    suspend fun getNews(queries: Map<String, String>): Response<NewsResult> {
        return newsRemoteApi.getNews(queries)
    }
}