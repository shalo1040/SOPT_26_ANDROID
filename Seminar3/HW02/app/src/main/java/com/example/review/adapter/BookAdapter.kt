package com.example.review.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.review.R
import com.example.review.data.BookData
import com.example.review.viewholder.BookViewHolder

class BookAdapter(private val context: Context): RecyclerView.Adapter<BookViewHolder>() {
    var data = mutableListOf<BookData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_book, parent, false)
        return BookViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bindData(data[position])
    }

}