package com.sandeep.assignment.newsfeedapp.ui.viewmodels

import android.content.Context
import com.sandeep.assignment.newsfeedapp.data.datasource.NewsFeedApi
import com.sandeep.assignment.newsfeedapp.domain.repository.BBCNewsFeedRepositoryImpl
import com.sandeep.assignment.newsfeedapp.domain.usecases.GetBBCNewsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class BBCNewsListViewModelTest {
    val dispatcher = TestCoroutineDispatcher()
    private lateinit var mBBCNewsListViewModel: BBCNewsListViewModel
    private lateinit var getBBCNewsUseCase: GetBBCNewsUseCase
    //private lateinit var bbcRepository: BBCNewsFeedRepository
    private lateinit var bbcRepositoryImpl: BBCNewsFeedRepositoryImpl
    @Mock
    private lateinit var mContext: Context

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        val service = Mockito.mock(NewsFeedApi::class.java)
        bbcRepositoryImpl = BBCNewsFeedRepositoryImpl(service)
        getBBCNewsUseCase = GetBBCNewsUseCase(bbcRepositoryImpl)
        mBBCNewsListViewModel = BBCNewsListViewModel(mContext,getBBCNewsUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
    @Test
    fun test_getBBCNewsList(): Unit = runBlocking {
        /*val list = MutableLiveData<NetWorkResults<NewsFeedResponse>>()
        val arrList :NewsFeedResponse
        Mockito.`when`(getBBCNewsUseCase.invoke()).thenReturn(Response.success(arrList))
        mMainViewModel.getPOIData().value?.let { Truth.assertThat(it.size).isEqualTo(0) }*/
    }

}