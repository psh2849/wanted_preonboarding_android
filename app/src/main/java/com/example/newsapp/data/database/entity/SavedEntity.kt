package com.example.newsapp.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.newsapp.model.Article
import com.example.newsapp.util.Constants.Companion.SAVED_NEWS_TABLE

@Entity(tableName = SAVED_NEWS_TABLE)
class SavedEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var article: Article
)
