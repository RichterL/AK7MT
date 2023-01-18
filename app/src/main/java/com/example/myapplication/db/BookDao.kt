package com.example.myapplication.db

import androidx.room.*
import com.example.myapplication.network.Book
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(book: BookEntity)

    @Delete
    fun delete(book: BookEntity)

    @Query("SELECT * from books WHERE id = :id")
    fun getBook(id: String): BookEntity

    @Query("SELECT * from books")
    fun getAllBooks(): List<BookEntity>
}