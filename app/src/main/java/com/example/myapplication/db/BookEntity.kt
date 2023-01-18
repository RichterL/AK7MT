package com.example.myapplication.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
class BookEntity (
    @PrimaryKey
    val id: String,
    val title: String,
    val authors: String,
    val thumbnail: String,
)