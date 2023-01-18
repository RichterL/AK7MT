package com.example.myapplication.network

import com.google.gson.annotations.SerializedName

data class Book (
    val id: String,
    @SerializedName("volumeInfo")
    val bookInfo: BookInfo,
    val searchInfo: SearchInfo
)

data class BookInfo (
    val title: String,
    val subtitle: String,
    val description: String,
    val authors: List<String>,
    val pageCount: Int,
    val imageLinks: ImageLinks,
    val language: String,
    val previewLink: String,
    val infoLink: String,
    val webReaderLink: String
)

data class SearchInfo (
    val textSnippet: String
)

data class ImageLinks (
    val smallThumbnail: String,
    val thumbnail: String
)