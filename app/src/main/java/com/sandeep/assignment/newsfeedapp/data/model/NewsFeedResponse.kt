package com.sandeep.assignment.newsfeedapp.data.model

/**
 * Data class for holding news feed response
 */
data class NewsFeedResponse (
    val articles: MutableList<NewsArticleModel>,
    val status: String,
    val totalResults: Int
    )