package com.example.newsapp.ui.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.adapter.CategoryAdapter
import com.example.newsapp.databinding.FragmentCategoriesListBinding
import com.example.newsapp.ui.MainActivity
import com.example.newsapp.util.NetworkResult
import com.example.newsapp.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CategoriesListFragment : Fragment() {
    private val args: CategoriesListFragmentArgs by navArgs()
    private lateinit var newsViewModel: NewsViewModel

    private var _binding: FragmentCategoriesListBinding? = null
    private val binding get() = _binding!!

    private val mAdapter by lazy { CategoryAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        newsViewModel = ViewModelProvider(requireActivity())[NewsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCategoriesListBinding.inflate(inflater, container, false)
        setRecyclerView()

        (activity as MainActivity).supportActionBar?.title = "Category-" + args.category

        lifecycleScope.launch {
            requestNewsApiData()
        }
        return binding.root
    }

    private fun setRecyclerView() {
        binding.rvCategoryDetail.adapter = mAdapter
        binding.rvCategoryDetail.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun requestNewsApiData() {
        newsViewModel.getNews(newsViewModel.getCategoryQueries(args.category))
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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}