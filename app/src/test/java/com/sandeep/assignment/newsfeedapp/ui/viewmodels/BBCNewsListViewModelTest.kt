package com.sandeep.assignment.newsfeedapp.ui.viewmodels

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.sandeep.assignment.newsfeedapp.MockFileReader
import com.sandeep.assignment.newsfeedapp.data.datasource.NewsFeedApi
import com.sandeep.assignment.newsfeedapp.data.model.NewsFeedResponse
import com.sandeep.assignment.newsfeedapp.domain.repository.BBCNewsFeedRepositoryImpl
import com.sandeep.assignment.newsfeedapp.domain.usecases.GetBBCNewsUseCase
import com.sandeep.assignment.newsfeedapp.getOrAwaitValue
import com.sandeep.assignment.newsfeedappdemo.data.common.NetWorkResults
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection
import java.util.concurrent.TimeUnit

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class BBCNewsListViewModelTest {
    private val testDispatcher = TestCoroutineDispatcher()
    lateinit var mainViewModel: BBCNewsListViewModel

    lateinit var useCase: GetBBCNewsUseCase
    lateinit var bbcNewsFeedRepositoryImpl: BBCNewsFeedRepositoryImpl
    private var mockWebServer = MockWebServer()
    //private lateinit var apiService: NewsFeedApi
    //private val testDispatcher = TestCoroutineDispatcher()

    lateinit var newsFeedApi: NewsFeedApi
    @Mock
    lateinit var context: Context

    @get:Rule
    val instantTaskExecutionRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var apiUsersObserver: Observer<NetWorkResults<NewsFeedResponse>>

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testDispatcher)
        mockWebServer.start()
        newsFeedApi = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().apply {
                connectTimeout(60, TimeUnit.SECONDS)
                readTimeout(60, TimeUnit.SECONDS)
                writeTimeout(60, TimeUnit.SECONDS)
                //addInterceptor(requestInterceptor)
            }.build())
            .build()
            .create(NewsFeedApi::class.java)
        bbcNewsFeedRepositoryImpl = BBCNewsFeedRepositoryImpl(newsFeedApi)
        //useCase = mock(GetBBCNewsUseCase::class.java)
        useCase = GetBBCNewsUseCase(bbcNewsFeedRepositoryImpl)
        mainViewModel = BBCNewsListViewModel(context,useCase)
        mainViewModel.newsList.observeForever(apiUsersObserver)

    }

    @Test
    fun getAllMoviesTest() {
        runBlocking {
            /*val response = MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(MockFileReader("success_response.json").content)
            mockWebServer.enqueue(response)
            val product = newsFeedApi.getBBCNewsList().body()*/
            mainViewModel.newsList.observeForever(apiUsersObserver)
            //Mockito.`when`(useCase.invoke())
            //    .thenReturn(NetWorkResults.Success(NewsFeedResponse(mutableListOf(),"success",0)))


            //mainViewModel.mNewsList.observeForever (apiUsersObserver)

            mainViewModel.fetchAllBBCNews()
            //val result = mainViewModel.newsList.getOrAwaitValue()
            assertEquals(NetWorkResults.Success(NewsFeedResponse(mutableListOf(),"success",0)), mainViewModel.newsList)
            //
            //verify(apiUsersObserver).onChanged(Results.Loading())


            //mainViewModel.mNewsList.removeObserver(apiUsersObserver)
            //mainViewModel.fetchAllMyProducts().o
            //verify(gfgApi).getGfgUser()
            //assertEquals(mainViewModel.mNewsList.getOrAwaitValue().message, Results.Loading<NewsFeedResponse>().message)
            //viewModel.getGfgUser().removeObserver(apiUsersObserver)
            //assertEquals(result.message,product)
            //Assert.assertNotNull(mainViewModel.mNewsList.getOrAwaitValue,product?.articles)
        }


        /* runBlocking {
             Mockito.`when`(useCase.invoke())
                 .thenReturn(Results.Success(NewsFeedResponse( mutableListOf(),"",0)))
             mainViewModel.fetchAllMyProducts()
             val result = mainViewModel.mNewsList.getOrAwaitValue()
             assertEquals(NewsFeedResponse( mutableListOf(),"",0), result)
         }*/

    }


    /*@Test
    fun `empty movie list test`() {
        runBlocking {
            Mockito.`when`(mainRepository.getAllMovies())
                .thenReturn(NetworkState.Success(listOf<Movie>()))
            mainViewModel.getAllMovies()
            val result = mainViewModel.movieList.getOrAwaitValue()
            assertEquals(listOf<Movie>(), result)
        }
    }*/
    @After
    fun teardown() {
        mockWebServer.shutdown()
    }
}