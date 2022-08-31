package com.sandeep.assignment.newsfeedapp.domain.usecases



import com.sandeep.assignment.newsfeedapp.data.model.NewsFeedResponse

import com.sandeep.assignment.newsfeedapp.domain.repository.BBCNewsFeedRepositoryImpl
import com.sandeep.assignment.newsfeedappdemo.data.common.NetWorkResults
import javax.inject.Inject

class GetBBCNewsUseCase @Inject constructor(private val bbcNewsFeedRepositoryImpl: BBCNewsFeedRepositoryImpl) {

    suspend fun invoke(): NetWorkResults<NewsFeedResponse> {
        return bbcNewsFeedRepositoryImpl.getAllNewsFeedsBBC()
    }

}