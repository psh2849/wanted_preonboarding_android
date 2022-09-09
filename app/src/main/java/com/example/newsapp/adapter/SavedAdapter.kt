package com.example.newsapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.data.database.entity.SavedEntity
import com.example.newsapp.databinding.SavedRowLayoutBinding
import com.example.newsapp.util.NewsDiffUtil

class SavedAdapter : RecyclerView.Adapter<SavedAdapter.SaveViewHolder>() {

    private var savedList = emptyList<SavedEntity>()

    class SaveViewHolder(private val binding: SavedRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(result: SavedEntity) {
            binding.savedEntity = result
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): SaveViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = SavedRowLayoutBinding.inflate(layoutInflater, parent, false)

                return SaveViewHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SaveViewHolder {
        return SaveViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: SaveViewHolder, position: Int) {
        holder.bind(savedList[position])
    }

    override fun getItemCount(): Int {
        return savedList.size
    }

    fun setData(newData: List<SavedEntity>) {
        val newsDiffUtil = NewsDiffUtil(savedList, newData)
        val diffUtilResult = DiffUtil.calculateDiff(newsDiffUtil)
        savedList = newData
        diffUtilResult.dispatchUpdatesTo(this)
    }
}