package com.sritechsoftsolutions.googlenewsapi

import retrofit2.Call
import retrofit2.http.GET

interface NewsApi {
    @GET("v2/top-headlines?sources=google-news&apiKey=9313274f01b142b89dba76c3c742077b")
    fun getNews():Call<Articles>
}