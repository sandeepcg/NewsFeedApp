package com.sandeep.assignment.newsfeedapp.data.datasource


import com.sandeep.assignment.newsfeedapp.data.model.NewsFeedResponse
import com.sandeep.assignment.newsfeedapp.utils.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsFeedApi {

    @GET("v2/top-headlines")
    suspend fun getBBCNewsList(
        @Query("sources") countryCode: String = "bbc-news",
        @Query("apiKey") apiKey: String = API_KEY
    ): Response<NewsFeedResponse>
}