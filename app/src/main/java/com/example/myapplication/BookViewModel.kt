package com.example.myapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.network.Book

data class BookViewModel(
    val image: Int,
    val text: String
)