package com.tc.codingchallenge.data.repoImpl

import com.tc.codingchallenge.data.model.quotesModel
import com.tc.codingchallenge.data.netwrok.ApiService
import com.tc.codingchallenge.domain.repo.QuotesRepo
import retrofit2.Response
import javax.inject.Inject

class QuotesRepoImpl @Inject constructor(private val apiService: ApiService):QuotesRepo{
    override suspend fun getAllQuotes(): Response<quotesModel> {
        return apiService.getQuotes()
    }
}