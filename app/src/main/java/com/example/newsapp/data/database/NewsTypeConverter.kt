package com.example.newsapp.data.database

import androidx.room.TypeConverter
import com.example.newsapp.model.Article
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class NewsTypeConverter {

    var gson = Gson()

    @TypeConverter
    fun articleToString(article: Article): String {
        return gson.toJson(article)
    }

    @TypeConverter
    fun stringToArticle(data: String): Article {
        val listType = object : TypeToken<Article>() {}.type
        return gson.fromJson(data, listType)
    }
}