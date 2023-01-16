package com.example.myapplication.network

import com.google.gson.annotations.SerializedName

data class Book (
    val id: String,
    @SerializedName("volumeInfo")
    val bookInfo: BookInfo
)

data class BookInfo (
    val title: String
)