package com.tc.codingchallenge.domain.repo

import com.tc.codingchallenge.data.model.quotesModel
import retrofit2.Response

interface QuotesRepo {
    suspend fun getAllQuotes():Response<quotesModel>
}