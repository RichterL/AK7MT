package com.example.myapplication.network

import com.google.gson.annotations.SerializedName

data class BookList(
    @SerializedName("items")
    val items: List<Book>
)
