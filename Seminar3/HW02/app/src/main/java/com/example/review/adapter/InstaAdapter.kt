package com.example.review.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.review.viewholder.InstaViewHolder
import com.example.review.R
import com.example.review.data.InstaData

class InstaAdapter(private val context : Context) : RecyclerView.Adapter<InstaViewHolder>() {
    var data = mutableListOf<InstaData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InstaViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_insta, parent, false)
        return InstaViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: InstaViewHolder, position: Int) {
        holder.bind(data[position])
    }

}