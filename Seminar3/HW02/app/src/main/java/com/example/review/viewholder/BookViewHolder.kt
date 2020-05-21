package com.example.review.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.review.R
import com.example.review.data.BookData

class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val img_main = itemView.findViewById<ImageView>(R.id.img_main)
    val tv_title = itemView.findViewById<TextView>(R.id.tv_title)
    val tv_score = itemView.findViewById<TextView>(R.id.tv_score)
    val tv_author = itemView.findViewById<TextView>(R.id.tv_author)

    fun bindData(data: BookData) {
        Glide.with(itemView).load(data.image).into(img_main)
        tv_title.text = data.title
        tv_score.text = data.score
        tv_author.text = data.author
    }
}