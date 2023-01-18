package com.example.myapplication.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.network.Book

@Database(entities = [BookEntity::class], version = 2, exportSchema = false)
abstract class BookDatabase : RoomDatabase() {
    abstract val bookDao : BookDao

//    companion object {
//        @Volatile
//        private var Instance: BookDatabase? = null
//
//        fun getDatabase(context: Context): BookDatabase {
//            return Instance ?: synchronized(this) {
//                Room.databaseBuilder(context, BookDatabase::class.java, "book_database")
//                    .fallbackToDestructiveMigration()
//                    .build()
//                    .also { Instance = it }
//            }
//        }
//    }
}

private lateinit var INSTANCE: BookDatabase

fun getDatabase(context: Context): BookDatabase {
    synchronized(BookDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                BookDatabase::class.java,
                "books")
                .fallbackToDestructiveMigration()
                .build()
        }
    }
    return INSTANCE
}