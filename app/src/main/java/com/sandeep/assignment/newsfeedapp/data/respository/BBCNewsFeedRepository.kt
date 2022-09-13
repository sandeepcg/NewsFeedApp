package com.sandeep.assignment.newsfeedapp.data.respository

/**
 * Interface for BBC NewsFeeds API call
 */

import com.sandeep.assignment.newsfeedapp.data.model.NewsFeedResponse
import com.sandeep.assignment.newsfeedapp.data.common.NetWorkResults


interface BBCNewsFeedRepository   {

   suspend fun getAllNewsFeedsBBC() : NetWorkResults<NewsFeedResponse>

}