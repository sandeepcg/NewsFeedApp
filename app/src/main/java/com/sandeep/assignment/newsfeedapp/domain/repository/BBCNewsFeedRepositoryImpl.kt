package com.sandeep.assignment.newsfeedapp.domain.repository

import com.sandeep.assignment.newsfeedapp.data.datasource.NewsFeedApi
import com.sandeep.assignment.newsfeedapp.data.model.NewsArticleModel
import com.sandeep.assignment.newsfeedapp.data.model.NewsFeedResponse
import com.sandeep.assignment.newsfeedapp.data.model.NewsSourceModel
import com.sandeep.assignment.newsfeedapp.data.respository.BBCNewsFeedRepository
import com.sandeep.assignment.newsfeedapp.utils.DateConverter
import com.sandeep.assignment.newsfeedappdemo.data.common.NetWorkResults
import javax.inject.Inject

class BBCNewsFeedRepositoryImpl @Inject constructor(private val newsFeedApi: NewsFeedApi) :
    BBCNewsFeedRepository {
    override suspend fun getAllNewsFeedsBBC(): NetWorkResults<NewsFeedResponse> {
        val response = newsFeedApi.getBBCNewsList()

        if (response.isSuccessful) {
            val body = response.body()!!
            val newsLists = mutableListOf<NewsArticleModel>()
            var sourceF: NewsSourceModel?
            body.articles?.forEach { newsListResponse ->
                sourceF = NewsSourceModel(newsListResponse.source.id, newsListResponse.source.name)
                newsLists.add(
                    NewsArticleModel(
                        newsListResponse.author,
                        newsListResponse.title,
                        newsListResponse.description,
                        newsListResponse.url,
                        newsListResponse.urlToImage,
                        DateConverter.changeDateFormat(newsListResponse.publishedAt),
                        newsListResponse.content,
                        sourceF!!
                    )
                )

            }
            //newsLists.sortBy{ it.publishedAt } //code to sort the news by descending order(old news first)
            body.articles.clear()
            body.articles.addAll(newsLists)
            return NetWorkResults.Success(body)
        } else{
            return NetWorkResults.Error(response.message())
        }
    }
}