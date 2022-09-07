package com.example.newsapp.model


import com.google.gson.annotations.SerializedName

data class Article(
    @SerializedName("author")
    val author: String?,
    @SerializedName("content")
    val content: String?,
    @SerializedName("publishedAt")
    val publishedAt: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("urlToImage")
    val urlToImage: String
)