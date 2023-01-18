package com.example.myapplication.db

import com.example.myapplication.network.Book
import kotlinx.coroutines.flow.Flow

interface BookRepository {
    fun getAllBooksStream(): List<BookEntity>

    fun getBookStream(id: String): BookEntity?

    suspend fun insertBook(book: BookEntity)

    suspend fun deleteBook(book: BookEntity)

}

class DefaultBookRepository(private val bookDao: BookDao): BookRepository {
    override fun getAllBooksStream(): List<BookEntity> = bookDao.getAllBooks()

    override fun getBookStream(id: String): BookEntity? = bookDao.getBook(id)

    override suspend fun insertBook(book: BookEntity) = bookDao.insert(book)

    override suspend fun deleteBook(book: BookEntity) = bookDao.delete(book)
}