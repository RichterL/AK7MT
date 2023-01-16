package com.example.myapplication

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.myapplication.network.Book

//@BindingAdapter("imageUrl")
//fun bindImage(imgView: ImageView, imgUrl: String?) {
//    imgUrl?.let{
//        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
//        imgView.load(imgUri) {
//            placeholder(R.drawable.loading_animation)
//            error(R.drawable.ic_broken_image)
//        }
//    }
//}
//
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Book>?) {
    val adapter = recyclerView.adapter as BookAdapter
//    adapter.submitList(data)
}