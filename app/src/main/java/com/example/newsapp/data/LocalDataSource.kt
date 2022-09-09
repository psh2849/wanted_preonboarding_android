package com.example.newsapp.data

import com.example.newsapp.data.database.NewsDao
import com.example.newsapp.data.database.entity.SavedEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val newsDao: NewsDao
) {

    suspend fun insertNews(savedEntity: SavedEntity) {
        newsDao.insertNews(savedEntity)
    }

    suspend fun deleteNews(savedEntity: SavedEntity) {
        newsDao.deleteNews(savedEntity)
    }

    fun readNews(): Flow<List<SavedEntity>> {
        return newsDao.readNews()
    }
}