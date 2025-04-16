package com.tc.codingchallenge.data.model


import com.google.gson.annotations.SerializedName

data class QuoteModel(
    @SerializedName("author")
    val author: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("quote")
    val quote: String? = null
)