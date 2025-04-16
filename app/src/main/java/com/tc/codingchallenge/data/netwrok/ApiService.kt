package com.tc.codingchallenge.data.netwrok

import com.tc.codingchallenge.data.model.quotesModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("quotes")
    suspend fun getQuotes():Response<quotesModel>
}