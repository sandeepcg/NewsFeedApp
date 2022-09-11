package com.sandeep.assignment.newsfeedapp.di

/**
 * Modules for handling dependency injections
 */
import com.sandeep.assignment.newsfeedapp.data.datasource.NewsFeedApi
import com.sandeep.assignment.newsfeedapp.data.respository.BBCNewsFeedRepository
import com.sandeep.assignment.newsfeedapp.domain.repository.BBCNewsFeedRepositoryImpl
import com.sandeep.assignment.newsfeedapp.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NewsApiModule {
    val baseURL = BASE_URL

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor{
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }


    @Singleton
    @Provides
    fun provideRetrofit(httpClient : OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
    }
    @Singleton
    @Provides
    fun provideOkHttpClient(requestInterceptor: HttpLoggingInterceptor) : OkHttpClient{
        return OkHttpClient.Builder().apply {
            connectTimeout(60, TimeUnit.SECONDS)
            readTimeout(60, TimeUnit.SECONDS)
            writeTimeout(60, TimeUnit.SECONDS)
            addInterceptor(requestInterceptor)
        }.build()
    }

    @Singleton
    @Provides
    fun provideNewsFetchApi(retrofit: Retrofit) : NewsFeedApi {
        return retrofit.create(NewsFeedApi::class.java)
    }

    @Singleton
    @Provides
    fun provideNewsListRepositoryImp(newListApi : NewsFeedApi) : BBCNewsFeedRepository {
        return BBCNewsFeedRepositoryImpl(newListApi)
    }
}