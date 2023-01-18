package com.example.myapplication

import android.app.Application
import androidx.lifecycle.*
import com.example.myapplication.db.BookEntity
import com.example.myapplication.db.getDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookStarViewModel(application: Application): ViewModel() {
    private val _books = MutableLiveData<List<BookEntity>>()
    val books: LiveData<List<BookEntity>> = _books

    init {
        val database = getDatabase(application)

        viewModelScope.launch(Dispatchers.IO) {
            _books.postValue(database.bookDao.getAllBooks())
        }
    }
}

