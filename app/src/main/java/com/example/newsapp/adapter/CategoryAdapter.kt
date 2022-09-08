package com.example.newsapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.databinding.CategoryRowLayoutBinding
import com.example.newsapp.model.Article
import com.example.newsapp.model.NewsResult
import com.example.newsapp.util.NewsDiffUtil

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private var newsList = emptyList<Article>()

    class CategoryViewHolder(private val binding: CategoryRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(result: Article) {
            binding.article = result
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): CategoryViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CategoryRowLayoutBinding.inflate(layoutInflater, parent, false)
                return CategoryViewHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
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