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


        if (quotes.isEmpty()) {
            emit(Uistate.ERROR(Exception("Quotes list is empty")))
            return@flow
        }


        val lowercaseQuery = query.trim().lowercase()

        val filteredQuotes = quotes.filter {
            it.author?.trim()?.lowercase()?.contains(lowercaseQuery) == true
        }

        if (filteredQuotes.isNotEmpty()) {
            emit(Uistate.SUCCESS(filteredQuotes))
        } else {
            emit(Uistate.ERROR(Exception("No quotes found matching the query")))
        }
    }
}