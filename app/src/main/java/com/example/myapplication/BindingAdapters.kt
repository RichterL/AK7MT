package com.example.myapplication

import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.myapplication.db.BookEntity
import com.example.myapplication.network.Book
//import com.example.myapplication.network.Book
import com.example.myapplication.network.BookList

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let{
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri) {
//            placeholder(R.drawable.loading_animation)
//            error(R.drawable.ic_broken_image)
        }
    }
}

@BindingAdapter("authorList")
fun bindText(textView: TextView, data: List<String>) {
    textView.text = data.toString()
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Book>?) {
    val adapter = recyclerView.adapter as BookAdapter
    adapter.submitList(data)
}

@BindingAdapter("starData")
fun bindRecyclerView2(recyclerView: RecyclerView, data: List<BookEntity>?) {
    val adapter = recyclerView.adapter as BookStarAdapter
    Log.i("BindingAdapter", "receieved data: ${data?.size}")
    adapter.submitList(data)
}