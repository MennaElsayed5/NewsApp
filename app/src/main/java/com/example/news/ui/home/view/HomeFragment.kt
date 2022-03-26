package com.example.news.ui.home.view

import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.news.R
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
    private lateinit var list: ArrayList<Article>
    private lateinit var adapter: NewsAdapter

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

    @SuppressLint("SetTextI18n")
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
                    if (it.data != null) {
                        setHasOptionsMenu(true)
                        adapter = NewsAdapter(
                            it.data.articles as ArrayList<Article>,
                            this@HomeFragment
                        )
                        list = it.data.articles
                        binding.newsRecycler.adapter = adapter
                    } else
                        binding.errorStatus.text = "No data found!"
                }
                Status.ERROR -> binding.errorStatus.text = it.msg
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.home_menu, menu)

        val searchManager =
            requireContext().getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.actionSearch)
            .actionView as SearchView

        searchView.maxWidth = Int.MAX_VALUE

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                filter(query.toString())
                return false
            }
        })

    }

    private fun filter(text: String) {
        val filteredArticles: ArrayList<Article> = ArrayList()

        for (s in list) {
            if (s.title!!.lowercase().contains(text.lowercase())) {
                filteredArticles.add(s)
            }
        }

        adapter.filterList(filteredArticles)
    }


    override fun onItemClickListener(pos: Int) {
        Log.d(TAG, "onItemClickListener: $pos")
    }

}