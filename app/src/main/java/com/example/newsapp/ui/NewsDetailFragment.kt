package com.example.newsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.navArgs
import com.example.newsapp.R
import com.example.newsapp.model.Article
import kotlinx.android.synthetic.main.activity_news_detail.*

class NewsDetailFragment: Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)
        // setToolbar()

        getBundleFromTopNewsFragment()

    }

    private fun getBundleFromTopNewsFragment() {
        val args = arguments
        val myBundle: Article? = args?.getP

    }

    private fun setToolbar() {
        setToolbar()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}