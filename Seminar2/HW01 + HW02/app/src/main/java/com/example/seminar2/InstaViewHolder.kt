package com.example.seminar2

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class InstaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) { //itemView는 xml 코드(InstaAdapter.kt의 onCreateViewHolder()에서 받은 것)
    val tv_userName = itemView.findViewById<TextView>(R.id.tv_username)
    val img_profile = itemView.findViewById<ImageView>(R.id.img_profile)
    val img_contents = itemView.findViewById<ImageView>(R.id.img_contents)

    fun bind(instaData: InstaData) {        //data class. 실제 데이터를 화면에 넣어주는 역할
        tv_userName.text = instaData.userName
        Glide.with(itemView).load(instaData.img_profile).into(img_profile)      //이미지 붙일 때 Glide
        Glide.with(itemView).load(instaData.img_contents).into(img_contents)
    }
}