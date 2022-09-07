package com.example.newsapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.databinding.TopNewsRowLayoutBinding
import com.example.newsapp.model.Article
import com.example.newsapp.model.NewsResult
import com.example.newsapp.util.NewsDiffUtil

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private var newsList = emptyList<Article>()

    class NewsViewHolder(private val binding: TopNewsRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(result: Article) {
            binding.article = result
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): NewsViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = TopNewsRowLayoutBinding.inflate(layoutInflater, parent, false)
                return NewsViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(newsList[position])
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    fun setData(newData: NewsResult) {
        val newsDiffUtil = NewsDiffUtil(newsList, newData.articles)
        val diffUtilResult = DiffUtil.calculateDiff(newsDiffUtil)
        newsList = newData.articles
        diffUtilResult.dispatchUpdatesTo(this)
    }
}