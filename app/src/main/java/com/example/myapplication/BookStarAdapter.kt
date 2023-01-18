package com.example.myapplication

import android.app.Application
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.BookItemViewStarBinding
import com.example.myapplication.db.BookEntity
import com.example.myapplication.db.getDatabase

class BookStarAdapter(private val application: Application): ListAdapter<BookEntity, BookStarAdapter.BookStarViewHolder>(DiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookStarViewHolder {
        return BookStarViewHolder(
            BookItemViewStarBinding.inflate(LayoutInflater.from(parent.context)),
            application
        )
    }

    override fun onBindViewHolder(holder: BookStarViewHolder, position: Int) {
        val book = getItem(position)
        holder.bind(book)


    }

    class BookStarViewHolder(
        private var binding: BookItemViewStarBinding,
        private var application: Application
    ) : RecyclerView.ViewHolder(binding.root) {
        private val database = getDatabase(application)

        fun bind(book: BookEntity) {
            binding.book = book
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<BookEntity>() {
        override fun areItemsTheSame(oldItem: BookEntity, newItem: BookEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: BookEntity, newItem: BookEntity): Boolean {
            return oldItem.id == newItem.id
        }

    }
}