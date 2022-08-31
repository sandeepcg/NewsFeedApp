package com.sandeep.assignment.newsfeedapp.data.model

data class NewsFeedResponse (
    val articles: MutableList<NewsArticleModel>,
    val status: String,
    val totalResults: Int
        )