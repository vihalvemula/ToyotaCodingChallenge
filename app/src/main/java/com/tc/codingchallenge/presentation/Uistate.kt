package com.tc.codingchallenge.presentation

sealed class Uistate<out T> {
    data class SUCCESS<T>(val data:T):Uistate<T>()
    data class ERROR(val error:Exception):Uistate<Nothing>()
    object LOADING:Uistate<Nothing>()
}