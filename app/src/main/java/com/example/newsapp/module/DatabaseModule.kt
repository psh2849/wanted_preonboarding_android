package com.example.newsapp.module

import android.content.Context
import androidx.room.Room
import com.example.newsapp.data.database.NewsDatabase
import com.example.newsapp.util.Constants.Companion.NEWS_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        NewsDatabase::class.java,
        NEWS_DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideDao(database: NewsDatabase) = database.newsDao()
}