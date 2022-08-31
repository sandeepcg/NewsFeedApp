package com.sandeep.assignment.newsfeedapp.data.respository

import com.sandeep.assignment.newsfeedapp.data.model.NewsFeedResponse
import com.sandeep.assignment.newsfeedappdemo.data.common.NetWorkResults




interface BBCNewsFeedRepository   {

   suspend fun getAllNewsFeedsBBC() : NetWorkResults<NewsFeedResponse>

}