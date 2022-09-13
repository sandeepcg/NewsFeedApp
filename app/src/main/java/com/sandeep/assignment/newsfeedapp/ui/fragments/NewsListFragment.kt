package com.sandeep.assignment.newsfeedapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sandeep.assignment.newsfeedapp.R
import com.sandeep.assignment.newsfeedapp.data.model.NewsArticleModel
import com.sandeep.assignment.newsfeedapp.ui.adapters.NewsListAdapter
import com.sandeep.assignment.newsfeedapp.ui.viewmodels.BBCNewsListViewModel

import com.sandeep.assignment.newsfeedapp.data.common.NetWorkResults

import dagger.hilt.android.AndroidEntryPoint

import javax.inject.Inject

@AndroidEntryPoint
class NewsListFragment : Fragment() {

    private val viewModel :BBCNewsListViewModel by viewModels()
    @Inject
    lateinit var bBCNewsListAdapter: NewsListAdapter
    private lateinit var bBCNewsListRecyclerView: RecyclerView
    private lateinit var loadingProgressBar: ContentLoadingProgressBar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.news_list_frament,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = findNavController().currentDestination?.label
        bBCNewsListRecyclerView = view.findViewById<RecyclerView>((R.id.newslist_recycleview))
        loadingProgressBar=view.findViewById<ContentLoadingProgressBar>(R.id.progressBar)
        bBCNewsListRecyclerView.hasFixedSize()
        bBCNewsListRecyclerView.layoutManager = LinearLayoutManager(view.context)
        bBCNewsListRecyclerView.adapter = bBCNewsListAdapter;
    }

    override fun onResume() {
        super.onResume()
        observe()
    }

    /**
     * Method for initialize live data observe method and news list fetch
     */
    private fun observe(){
        observeBBCNews()
        viewModel.fetchAllBBCNews()
    }

    /**
     * Method for live data observer
      */
    private fun observeBBCNews(){
        viewModel.newsList.observe(viewLifecycleOwner, { results ->
            when (results) {
                is NetWorkResults.Loading -> {
                    loadingProgressBar.visibility =View.VISIBLE
                }
                is NetWorkResults.Error -> {
                    results.message?.let {
                        showToast(it) }
                    loadingProgressBar.visibility =View.GONE
                }
                is NetWorkResults.Success -> {
                    loadingProgressBar.visibility =View.GONE
                    results.data?.let {
                        handleNewsList(results.data.articles) }
                }
            }
        } )
    }

    /**
     * Method for displaying toast message
     * @param message : instance of message text
     */
    private fun showToast(message: String) {
        Toast.makeText(activity,message,Toast.LENGTH_SHORT).show()
    }

    /**
     * Method for set new list data to adapter
     * @param newsList : instance of news list
     */
    private fun handleNewsList(newsList: List<NewsArticleModel>){
        bBCNewsListAdapter.setListData(newsList)
    }

}