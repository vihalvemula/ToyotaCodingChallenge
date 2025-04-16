package com.tc.codingchallenge.data.model


import com.google.gson.annotations.SerializedName

data class quotesModel(
    @SerializedName("limit")
    val limit: Int? = null,
    @SerializedName("quotes")
    val quotes: List<QuoteModel?>? = null,
    @SerializedName("skip")
    val skip: Int? = null,
    @SerializedName("total")
    val total: Int? = null
)