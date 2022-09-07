package com.example.newsapp.ui.topnews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.adapter.NewsAdapter
import com.example.newsapp.databinding.FragmentTopNewsBinding
import com.example.newsapp.util.NetworkResult
import com.example.newsapp.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TopNewsFragment : Fragment() {
    private lateinit var newsViewModel: NewsViewModel

    private var _binding: FragmentTopNewsBinding? = null
    private val binding get() = _binding!!

    private val mAdapter by lazy { NewsAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        newsViewModel = ViewModelProvider(requireActivity())[NewsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTopNewsBinding.inflate(inflater, container, false)
        setRecyclerView()

        lifecycleScope.launch {
            requestNewsApiData()
        }

        return binding.root
    }

    private fun setRecyclerView() {
        binding.rvTopNews.adapter = mAdapter
        binding.rvTopNews.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun requestNewsApiData() {
        newsViewModel.getNews(newsViewModel.getQueries())
        newsViewModel.newsResponse.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    response.data?.let { mAdapter.setData(it) }
                }

                is NetworkResult.Error -> {
                    Toast.makeText(
                        requireContext(),
                        response.message.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }

                is NetworkResult.Loading -> {
                }
            }
        }
    }
}