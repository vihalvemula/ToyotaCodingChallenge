package com.tc.codingchallenge.domain.usecase

import com.tc.codingchallenge.data.model.QuoteModel
import com.tc.codingchallenge.data.model.quotesModel
import com.tc.codingchallenge.domain.repo.QuotesRepo
import com.tc.codingchallenge.presentation.Uistate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class GetQuotesUsecase @Inject constructor(private val quotesRepo: QuotesRepo) {
    operator fun invoke():Flow<Uistate<List<QuoteModel>>> = flow {
        emit(Uistate.LOADING)
        val response = quotesRepo.getAllQuotes()
        //val body = response.body()
        if(response.isSuccessful){
            val result = response.body()?.quotes?.filterNotNull()?: emptyList()
            emit(Uistate.SUCCESS(result))
        }else{
            emit(Uistate.ERROR(Exception("Something went wrong")))
        }

    }.catch { e->
        emit(Uistate.ERROR(Exception(e)))

    }
}