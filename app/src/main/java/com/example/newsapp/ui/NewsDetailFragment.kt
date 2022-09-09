package com.example.newsapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.newsapp.R
import com.example.newsapp.bindingadapter.NewsRowBinding
import com.example.newsapp.data.database.entity.SavedEntity
import com.example.newsapp.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_news_detail.view.*

@AndroidEntryPoint
class NewsDetailFragment : Fragment() {
    private val newsViewModel : NewsViewModel by viewModels()
    private var isNewsSaved = false
    private var savedId = 0
    private val args by navArgs<NewsDetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_news_detail, container, false)

        getArgsFromTopNewsFragment(view)

        view.iv_detail_star.setOnClickListener {
            clickSavedStarButton(view)
        }

        return view
    }

    private fun clickSavedStarButton(view: View) {
        if(!isNewsSaved) {
            saveToNews(view)
        } else {
            deleteToNews(view)
        }
    }

    private fun saveToNews(view: View) {
        val savesEntity = SavedEntity(
            0,
            args.article
        )

        newsViewModel.insertSavedNews(savesEntity)
        view.iv_detail_star.setImageResource(R.drawable.ic_selected_star)
        Toast.makeText(context, "Save News!", Toast.LENGTH_SHORT).show()
        isNewsSaved = true
    }



    private fun deleteToNews(view: View) {
        val savesEntity = SavedEntity(
            savedId,
            args.article
        )

        newsViewModel.deleteSavedNews(savesEntity)
        view.iv_detail_star.setImageResource(R.drawable.ic_no_selected_star)
        Toast.makeText(context, "Delete News!", Toast.LENGTH_SHORT).show()
        isNewsSaved = false
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

        checkSelectedStar(view)
    }

    private fun checkSelectedStar(view: View) {
        newsViewModel.readSavedNews.observe(viewLifecycleOwner) { newsEntity ->
            try {
                for(savedNews in newsEntity) {
                    if(savedNews.article.url == args.article.url) {
                        view.iv_detail_star.setImageResource(R.drawable.ic_selected_star)
                        savedId = savedNews.id
                        isNewsSaved = true
                        break
                    } else {
                        view.iv_detail_star.setImageResource(R.drawable.ic_no_selected_star)
                    }
                }
            } catch (e: Exception) {
                Log.d("NewsDetailFragment", e.message.toString())
            }
        }
    }
}