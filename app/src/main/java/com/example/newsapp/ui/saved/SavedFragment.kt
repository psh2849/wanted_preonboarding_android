package com.example.newsapp.ui.saved

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.adapter.CategoryAdapter
import com.example.newsapp.adapter.SavedAdapter
import com.example.newsapp.databinding.FragmentCategoriesListBinding
import com.example.newsapp.databinding.FragmentSavedBinding
import com.example.newsapp.model.NewsResult
import com.example.newsapp.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SavedFragment : Fragment() {
    private lateinit var newsViewModel: NewsViewModel

    private var _binding: FragmentSavedBinding? = null
    private val binding get() = _binding!!

    private val mAdapter by lazy { SavedAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        newsViewModel = ViewModelProvider(requireActivity())[NewsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSavedBinding.inflate(inflater, container, false)
        setRecyclerView()

        lifecycleScope.launch {
            newsViewModel.readSavedNews.observe(viewLifecycleOwner) { saved ->
                mAdapter.setData(saved)
            }
        }

        return binding.root
    }

    private fun setRecyclerView() {

        binding.rvSaved.adapter = mAdapter
        binding.rvSaved.layoutManager = LinearLayoutManager(requireContext())
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}