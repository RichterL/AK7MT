package com.example.myapplication

import android.app.Application
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.ListAdapter
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.BookItemViewBinding
import com.example.myapplication.db.BookDatabase
import com.example.myapplication.db.BookEntity
import com.example.myapplication.db.getDatabase
import com.example.myapplication.network.Book
import com.example.myapplication.network.BookList
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.coroutineContext

class BookAdapter(private val application: Application): ListAdapter<Book, BookAdapter.BookViewHolder>(DiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        return BookViewHolder(BookItemViewBinding.inflate(LayoutInflater.from(parent.context)), application)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = getItem(position)
        holder.bind(book)

    }

    class BookViewHolder(private var binding: BookItemViewBinding, private var application: Application): RecyclerView.ViewHolder(binding.root) {
        private val database = getDatabase(application)

        fun bind(book: Book) {
            binding.book = book
            binding.previewButton.setOnClickListener{
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(book.bookInfo.infoLink)
                itemView.context.startActivity(intent)
            }


            binding.starButton.setOnClickListener{
                Log.i("BookAdapter", "star clicked")
                val scope = CoroutineScope(Dispatchers.Default)

                scope.launch {
                    Log.i("BookAdapter", "saving book")
                    database.bookDao.insert(BookEntity(book.id, book.bookInfo.title, book.bookInfo.authors.toString(), book.bookInfo.imageLinks.thumbnail))
                    Log.i("BookAdapter", "book saved")
                    Snackbar.make(binding.root, "Book saved to starred books", Snackbar.LENGTH_LONG)
                        .setAnimationMode(Snackbar.ANIMATION_MODE_SLIDE)
                        .setAction("Action", null).show()
                }
            }

            binding.executePendingBindings()
        }
    }

    companion object DiffCallback: DiffUtil.ItemCallback<Book>() {
        override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem.id == newItem.id
        }

    }
}