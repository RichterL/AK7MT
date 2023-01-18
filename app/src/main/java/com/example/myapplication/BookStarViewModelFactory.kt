package com.example.myapplication

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class BookStarViewModelFactory(val application: Application): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookStarViewModel::class.java)) {
            return BookStarViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}