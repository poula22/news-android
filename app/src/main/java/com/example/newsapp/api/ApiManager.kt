package com.example.newsapp.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiManager {
    companion object{
        var logging: HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(
            HttpLoggingInterceptor.Level.BASIC);
        var client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
        private val BASEURL:String="https://newsapi.org/"
        private var retrofit:Retrofit?=null
        private fun getInstance():Retrofit{
            if (retrofit==null)
            retrofit=Retrofit.Builder().baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client).build()
            return retrofit!!
        }
        fun getApis():WebService{
            return getInstance().create(WebService::class.java)
        }
    }

}