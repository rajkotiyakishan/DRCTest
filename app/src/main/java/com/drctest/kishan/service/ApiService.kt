package com.drctest.kishan.service

import com.drctest.kishan.responsemodel.NewsResponse
import io.reactivex.Single
import retrofit2.http.*


interface ApiService {

    @GET("top-headlines?sources=google-news")
    fun getNews( @Query("apiKey") apiKey: String): Single<NewsResponse>

}