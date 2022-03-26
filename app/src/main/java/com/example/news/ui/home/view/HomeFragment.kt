package com.example.news.ui.home.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.news.data.remote.NetworkProvider
import com.example.news.databinding.HomeFragmentBinding
import com.example.news.model.Article
import com.example.news.model.network.Status
import com.example.news.ui.home.viewmodel.HomeViewModel
import com.example.news.ui.home.viewmodel.HomeViewModelFactory

private const val TAG = "HomeFragment"

class HomeFragment : Fragment(), NewsAdapter.NewsListListener {

    private lateinit var binding: HomeFragmentBinding
    private lateinit var navController: NavController

    private val viewModel by viewModels<HomeViewModel> {
        HomeViewModelFactory(networkInterface = NetworkProvider)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        binding.newsRecycler.layoutManager = LinearLayoutManager(requireContext())

        viewModel.getNews().observe(viewLifecycleOwner) {
            when (it.status) {
                Status.LOADING -> {
                    binding.progress.visibility = View.VISIBLE
                    binding.errorStatus.text = ""
                }
                Status.SUCCESS -> {
                    binding.progress.visibility = View.INVISIBLE
                    if (it.data != null)
                        binding.newsRecycler.adapter = NewsAdapter(
                            it.data.articles as ArrayList<Article>,
                            this@HomeFragment
                        )
                    else
                        binding.errorStatus.text = "No data found!"
                }
                Status.ERROR -> binding.errorStatus.text = it.msg
            }
        }
    }

    override fun onItemClickListener(pos: Int) {
        Log.d(TAG, "onItemClickListener: $pos")
    }

}