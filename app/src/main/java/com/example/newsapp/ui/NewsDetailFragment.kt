package com.example.newsapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.newsapp.R
import com.example.newsapp.bindingadapter.NewsRowBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_news_detail.view.*

@AndroidEntryPoint
class NewsDetailFragment : Fragment() {
    private val args by navArgs<NewsDetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_news_detail, container, false)

        getArgsFromTopNewsFragment(view)
        return view
    }

    private fun getArgsFromTopNewsFragment(view: View) {
        val result = args.article

        view.tv_detail_title.text = result.title
        NewsRowBinding.setTextOfTime(view.tv_detail_time, result.publishedAt)

        result.author?.let { author ->
            view.tv_detail_author.text = author
        }

        result.urlToImage?.let { image ->
            view.iv_detail_image.load(image) {
                crossfade(600)
                error(R.drawable.ic_error)
            }
        }

        result.content?.let { content ->
            view.tv_detail_content.text = content
        }

    }
}