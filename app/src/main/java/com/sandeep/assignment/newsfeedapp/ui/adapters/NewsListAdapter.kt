package com.sandeep.assignment.newsfeedapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sandeep.assignment.newsfeedapp.R
import com.sandeep.assignment.newsfeedapp.data.model.NewsArticleModel
import javax.inject.Inject

class NewsListAdapter @Inject constructor() : RecyclerView.Adapter<NewsListViewHolder> (){

    var listNews  = mutableListOf<NewsArticleModel>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsListViewHolder {
        return NewsListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.news_list_item,parent,false))
    }

    override fun onBindViewHolder(holder: NewsListViewHolder, position: Int) {
        holder.bind(listNews?.get(position)!!)
    }

    override fun getItemCount(): Int {
        if (listNews == null) return 0
        return listNews?.size!!
    }

    fun setListData(listArticle: List<NewsArticleModel>) {
        this.listNews = listArticle.toMutableList()
        notifyItemRangeInserted(0, listArticle.size)
    }
}

class NewsListViewHolder (view : View): RecyclerView.ViewHolder(view){

    private val newsTitle = view.findViewById<TextView>(R.id.title)
    private val newsAuthor = view.findViewById<TextView>(R.id.newsauthor)
    private val newsPublisheddate = view.findViewById<TextView>(R.id.newspublisheddate)
    private val newsSource = view.findViewById<TextView>(R.id.source)
    private val newsImg = view.findViewById<ImageView>(R.id.newsimage)

    fun bind(dataNews: NewsArticleModel) {
        newsTitle.text = dataNews.title.toString()
        newsAuthor.text = dataNews.author.toString()
        newsPublisheddate.text = dataNews.publishedAt
        newsSource.text= "Source : ${dataNews.source.name}"
        Glide.with(itemView)
            .load(dataNews.urlToImage)
            .into(newsImg)

    }
}
