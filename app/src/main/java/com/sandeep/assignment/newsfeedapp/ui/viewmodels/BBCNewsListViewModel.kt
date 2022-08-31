package com.sandeep.assignment.newsfeedapp.ui.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sandeep.assignment.newsfeedapp.data.model.NewsFeedResponse
import com.sandeep.assignment.newsfeedapp.domain.usecases.GetBBCNewsUseCase
import com.sandeep.assignment.newsfeedapp.utils.NetworkUtils.Companion.hasInternetConnection
import com.sandeep.assignment.newsfeedappdemo.data.common.NetWorkResults
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BBCNewsListViewModel @Inject constructor(@ApplicationContext private val mContext: Context,
                                               private val bbcNewsUseCase: GetBBCNewsUseCase) : ViewModel() {
    
    val newsList: MutableLiveData<NetWorkResults<NewsFeedResponse>> = MutableLiveData()

    fun fetchAllBBCNews() = viewModelScope.launch(Dispatchers.IO) {
        newsList.postValue(NetWorkResults.Loading())
        try {
            if (hasInternetConnection(mContext)) {
                val fetchResults = bbcNewsUseCase.invoke()
                newsList.postValue(fetchResults)
            } else {
                newsList.postValue(NetWorkResults.Error("Internet is not available"))
            }
        } catch (e: Exception) {
            newsList.postValue(NetWorkResults.Error(e.message.toString()))
        }
    }
}