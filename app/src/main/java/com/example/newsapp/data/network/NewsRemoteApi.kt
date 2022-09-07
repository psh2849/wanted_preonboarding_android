package com.example.newsapp.data.network

import com.example.newsapp.model.NewsResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface NewsRemoteApi {

    @GET("/v2/top-headlines")
    suspend fun getNews(
        @QueryMap queries: Map<String, String>
    ): Response<NewsResult>
}