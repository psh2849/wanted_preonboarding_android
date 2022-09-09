package com.example.newsapp.data.database

import androidx.room.*
import com.example.newsapp.data.database.entity.SavedEntity
import com.example.newsapp.util.Constants.Companion.SAVED_NEWS_TABLE
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(savedEntity: SavedEntity)

    @Delete
    suspend fun deleteNews(savedEntity: SavedEntity)

    @Query("SELECT * FROM $SAVED_NEWS_TABLE ORDER BY id ASC")
    fun readNews(): Flow<List<SavedEntity>>
}