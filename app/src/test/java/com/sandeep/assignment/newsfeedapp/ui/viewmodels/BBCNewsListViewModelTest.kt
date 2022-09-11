package com.sandeep.assignment.newsfeedapp.ui.viewmodels

import android.content.Context
import androidx.annotation.MainThread
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.google.common.truth.Truth
import com.sandeep.assignment.newsfeedapp.MockFileReader
import com.sandeep.assignment.newsfeedapp.data.datasource.NewsFeedApi
import com.sandeep.assignment.newsfeedapp.data.model.NewsFeedResponse
import com.sandeep.assignment.newsfeedapp.domain.repository.BBCNewsFeedRepositoryImpl
import com.sandeep.assignment.newsfeedapp.domain.usecases.GetBBCNewsUseCase
import com.sandeep.assignment.newsfeedapp.getOrAwaitValue
import com.sandeep.assignment.newsfeedapp.data.common.NetWorkResults
import com.sandeep.assignment.newsfeedapp.utils.SUCCESS_MSG
import io.mockk.every
import io.mockk.mockkStatic
import io.mockk.slot
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import retrofit2.Response
import javax.inject.Inject

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class BBCNewsListViewModelTest {

    private val testDispatcher = TestCoroutineDispatcher()
    lateinit var mainViewModel: BBCNewsListViewModel

    @Mock
    lateinit var useCase: GetBBCNewsUseCase
    @Inject
    lateinit var bbcNewsFeedRepositoryImpl: BBCNewsFeedRepositoryImpl
    private var mockWebServer = MockWebServer()

    //private lateinit var apiService: NewsFeedApi
    //private val testDispatcher = TestCoroutineDispatcher()
    @Mock
    lateinit var newsFeedApi: NewsFeedApi

    @Mock
    lateinit var context: Context

    @get:Rule
    val instantTaskExecutionRule: InstantTaskExecutorRule = InstantTaskExecutorRule()


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testDispatcher)
        mainViewModel = BBCNewsListViewModel(context, useCase)


    }



    @After
    fun teardown() {
        Dispatchers.resetMain()
        mockWebServer.shutdown()
    }

    @Test
    fun test_getPoiData() = runBlocking {
        //`when`(useCase.invoke()).
        //thenReturn(NetWorkResults.Success(NewsFeedResponse(mutableListOf(), "success", 0)))
       // mainViewModel.fetchAllBBCNews()
        //Assert.assertEquals(mainViewModel.newsList.value,NetWorkResults.Loading<NewsFeedResponse>())
        //Assert.assertNotNull(mainViewModel)
        //Assert.assertNotNull()
    //val list = MutableLiveData<ArrayList<Results>>()
        //val arrList :ArrayList<Results> = ArrayList()
        //Mockito.`when`(repository.getPOI(5, "km", 52.526, 13.415, "apiKey")).thenReturn(Response.success(arrList))
        //mMainViewModel.getPOIData().value?.let { Truth.assertThat(it.size).isEqualTo(0) }
    }

    @Test
    fun test_NonEmptyData() = runBlocking {

        mainViewModel.newsList.postValue(NetWorkResults.Success(NewsFeedResponse(mutableListOf(), SUCCESS_MSG, 0),SUCCESS_MSG))
        mainViewModel.fetchAllBBCNews()
        Assert.assertEquals(mainViewModel.newsList.value?.message,SUCCESS_MSG)
        Assert.assertNotNull(mainViewModel)

    }
}

