package com.tc.codingchallenge.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tc.codingchallenge.data.model.QuoteModel
import com.tc.codingchallenge.data.model.quotesModel
import com.tc.codingchallenge.domain.usecase.GetQuotesUsecase
import com.tc.codingchallenge.domain.usecase.SearchQuotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuotesViewModel @Inject constructor(
    private val getQuotesUsecase: GetQuotesUsecase,
    private val searchQuotesUseCase: SearchQuotesUseCase
):ViewModel() {
     private val _quotes = MutableStateFlow<Uistate<List<QuoteModel>>>(Uistate.LOADING)
    val quotes:StateFlow<Uistate<List<QuoteModel>>> = _quotes

    private val quotesList: MutableList<QuoteModel> = mutableListOf()

    fun getQuotes(){
        viewModelScope.launch {
            if(quotesList.isEmpty()){
                getQuotesUsecase().collect{
                    _quotes.value=it
                    if(it is Uistate.SUCCESS){
                        quotesList.addAll(it.data)
                    }
                }
            } else {
                _quotes.value = Uistate.SUCCESS(quotesList)
            }
        }
    }

    fun searchQuotes(query: String){
        viewModelScope.launch {
            searchQuotesUseCase(quotesList,query).collect{
                _quotes.value = it
            }
        }
    }


}