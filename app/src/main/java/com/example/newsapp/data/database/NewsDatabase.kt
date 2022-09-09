package com.example.newsapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newsapp.data.database.entity.SavedEntity

@Database(
    entities = [SavedEntity::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(NewsTypeConverter::class)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
}