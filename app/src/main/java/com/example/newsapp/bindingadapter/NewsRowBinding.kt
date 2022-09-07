package com.example.newsapp.bindingadapter

import android.annotation.SuppressLint
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.load
import com.example.newsapp.R
import java.text.SimpleDateFormat

class NewsRowBinding {
    companion object {
        @BindingAdapter("loadImageFromUrl")
        @JvmStatic
        fun loadImageFromUrl(imageView: ImageView, imageUrl: String) {
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
            Log.d("NewsRowBinding", splitTimes + " " + nowTime + " " + writeTime + " " + diff)

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