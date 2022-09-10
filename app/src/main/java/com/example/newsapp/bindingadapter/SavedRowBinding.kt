package com.example.newsapp.bindingadapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.newsapp.R
import com.example.newsapp.adapter.SavedAdapter
import com.example.newsapp.data.database.entity.SavedEntity
import com.example.newsapp.ui.saved.SavedFragmentDirections
import java.text.SimpleDateFormat

class SavedRowBinding {
    companion object {

        @BindingAdapter("viewVisibility", "setData", requireAll = false)
        @JvmStatic
        fun setDataAndViewVisibility(
            view: View,
            entity: List<SavedEntity>?,
            adapter: SavedAdapter?
        ) {
            if (entity.isNullOrEmpty()) {
                when (view) {
                    is ImageView -> {
                        view.visibility = View.VISIBLE
                    }

                    is TextView -> {
                        view.visibility = View.VISIBLE
                    }

                    is RecyclerView -> {
                        view.visibility = View.INVISIBLE
                    }
                }
            } else {
                when(view) {
                    is ImageView -> {
                        view.visibility = View.INVISIBLE
                    }

                    is TextView -> {
                        view.visibility = View.INVISIBLE
                    }

                    is RecyclerView -> {
                        view.visibility = View.VISIBLE
                        adapter?.setData(entity)
                    }
                }
            }
        }

        @BindingAdapter("onSavedClickListener")
        @JvmStatic
        fun onSavedClickListener(newsRowLayout: ConstraintLayout, entity: SavedEntity) {
            newsRowLayout.setOnClickListener {
                try {
                    val action =
                        SavedFragmentDirections.actionSavedFragmentToNewsDetailFragment(
                            entity.article
                        )
                    newsRowLayout.findNavController().navigate(action)
                } catch (e: Exception) {
                    Log.d("onSavedClickListener", e.toString())
                }
            }
        }

        @BindingAdapter("loadImageFromUrl")
        @JvmStatic
        fun loadImageFromUrl(imageView: ImageView, imageUrl: String?) {
            imageView.load(imageUrl) {
                crossfade(600)
                error(R.drawable.ic_error)
            }
        }

        @BindingAdapter("setTextOfTitle")
        @JvmStatic
        fun setTextOfTitle(textView: TextView, title: String) {
            textView.text = title
        }

        @BindingAdapter("setTextOfAuthor")
        @JvmStatic
        fun setTextOfAuthor(textView: TextView, author: String?) {
            textView.text = author
        }

        @SuppressLint("SimpleDateFormat", "SetTextI18n")
        @BindingAdapter("setTextOfTime")
        @JvmStatic
        fun setTextOfTime(textView: TextView, time: String) {
            val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            val nowTime = System.currentTimeMillis()
            val split = time.split("T")
            var splitTimes = (split[0] + " " + split[1]).substring(0, time.length - 1)

            val writeTime = format.parse(splitTimes).time
            var diff: Long = (nowTime - writeTime) / 1000

            if (diff < TimeValue.SEC.value)
                textView.text = "Just Now"
            else {
                for (i in TimeValue.values()) {
                    diff /= i.value
                    if (diff < i.maximum) {
                        when (i.names) {
                            "SEC" -> textView.text = "$diff minutes ago"
                            "MIN" -> textView.text = "$diff hours ago"
                            "HOUR" -> textView.text = "$diff days ago"
                            "DAY" -> textView.text = "$diff months ago"
                            "MONTH" -> textView.text = "$diff years ago"
                        }
                        break
                    }
                }
            }
        }
    }

    enum class TimeValue(val value: Int, val maximum: Int, val names: String) {
        SEC(60, 60, "SEC"),
        MIN(60, 24, "MIN"),
        HOUR(24, 30, "HOUR"),
        DAY(30, 12, "DAY"),
        MONTH(12, Int.MAX_VALUE, "MONTH")
    }
}