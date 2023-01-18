package com.example.myapplication

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.network.Book
import com.example.myapplication.network.BookApi
import com.example.myapplication.network.BookList
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Query

class BookViewModel(isbn: String): ViewModel() {
    var query = isbn
    private val _status = MutableLiveData<String>()
    val status: LiveData<String> = _status

    private val _book = MutableLiveData<Book>()
    val book: LiveData<Book> = _book

    private val _books = MutableLiveData<List<Book>>()
    val books: LiveData<List<Book>> = _books

    init {
        Log.i("BookViewModel", "BookViewModel created!")
        Log.i("BookViewModel", "searching for $isbn")

        viewModelScope.launch {
            try {
                val listResult = BookApi.retrofitService.getBooks(isbn)
                _book.value = listResult.items[0]
//                _status.value = "image URL: " + _book.value!!.bookInfo.imageLinks.thumbnail

                _status.value = "Success: " + listResult.items.size.toString()
                _books.value = listResult.items

                Log.i("BookViewModel", "found books: ${listResult.items.size}")
            } catch (e: Exception) {
                _status.value = "Failure: " + e.message
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("BookViewModel", "BookViewModel destroyed!")
    }
}