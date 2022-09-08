package com.example.newsapp.ui.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.newsapp.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_categories.view.*

@AndroidEntryPoint
class CategoriesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_categories, container, false)

        view.cl_business.setOnClickListener {
            putCategoryBundle(view, "business")
        }

        view.cl_entertain.setOnClickListener {
            putCategoryBundle(view, "entertainment")
        }

        view.cl_general.setOnClickListener {
            putCategoryBundle(view, "general")
        }

        view.cl_health.setOnClickListener {
            putCategoryBundle(view, "health")
        }

        view.cl_science.setOnClickListener {
            putCategoryBundle(view, "science")
        }

        view.cl_sports.setOnClickListener {
            putCategoryBundle(view, "sports")
        }

        view.cl_technology.setOnClickListener {
            putCategoryBundle(view, "technology")
        }

        return view
    }

    private fun putCategoryBundle(view: View, category: String) {
        val action =
            CategoriesFragmentDirections.actionCategoriesFragmentToCategoriesListFragment(category)
        view.findNavController().navigate(action)
    }
}