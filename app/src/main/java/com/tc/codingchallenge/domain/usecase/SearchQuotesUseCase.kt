package com.tc.codingchallenge.domain.usecase

import com.tc.codingchallenge.data.model.QuoteModel
import com.tc.codingchallenge.presentation.Uistate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class SearchQuotesUseCase @Inject constructor() {

    operator fun invoke(quotes: List<QuoteModel>, query: String): Flow<Uistate<List<QuoteModel>>> = flow {
        emit(Uistate.LOADING)
        if (quotes.isEmpty()){
            emit(Uistate.ERROR(Exception("List is empty")))
        }
        quotes.filter {
            it.author == query
        }.also {
            emit(Uistate.SUCCESS(it))
        }
    }

}