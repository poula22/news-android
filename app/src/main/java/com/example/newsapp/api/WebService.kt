package com.example.newsapp.api

import com.example.newsapp.api.module.SourcesResponse
import com.example.newsapp.api.module.ArticleResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {
    @GET("v2/top-headlines/sources")
    fun getNewsSources(@Query("apiKey") apiKey:String
                       ,@Query("category") category:String):Call<SourcesResponse>
    @GET("v2/everything")
    fun getNews(@Query("apiKey") apiKey:String
                ,@Query("sources") sources:String):Call<ArticleResponse>
}