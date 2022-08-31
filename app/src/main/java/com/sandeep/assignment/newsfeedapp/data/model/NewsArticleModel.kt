package com.sandeep.assignment.newsfeedapp.data.model



data class NewsArticleModel (
    var author: String,
    var title: String,
    var description: String,
    var url: String,
    var urlToImage: String,
    var publishedAt: String,
    var content: String,
    var source: NewsSourceModel
        )
