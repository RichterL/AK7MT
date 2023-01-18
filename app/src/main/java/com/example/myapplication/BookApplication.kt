package com.example.myapplication

import android.app.Application
import com.example.myapplication.db.BookDatabase

import com.example.myapplication.db.DefaultBookRepository

//class BookApplication: Application() {
//    val database by lazy { getDatabase(this) }
//    val repository by lazy { DefaultBookRepository(database.bookDao()) }
//}