package com.sandeep.assignment.newsfeedapp.data.datasource

import com.sandeep.assignment.newsfeedapp.MockFileReader
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.MockitoAnnotations
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class NewsFeedApiTest {
    private var mockWebServer = MockWebServer()
    private lateinit var apiService: NewsFeedApi
    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testDispatcher)
        mockWebServer.start()
        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient())
            .build()
            .create(NewsFeedApi::class.java)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun testBBCNewsListSuccess() {
        runBlocking {
            val response = MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(MockFileReader("success_response.json").content)
            mockWebServer.enqueue(response)

            val product = apiService.getBBCNewsList().body()
            assertNotNull(product)
            Assert.assertEquals(10,product?.articles?.size)

        }
    }

    @Test
    fun testBBCNewsListFail() {

        runBlocking {
            val response = MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(MockFileReader("failed_response.json").content)
            mockWebServer.enqueue(response)

            val product = apiService.getBBCNewsList().body()
            assertNotNull(product)
            Assert.assertEquals("error",product?.status)
        }
    }
}