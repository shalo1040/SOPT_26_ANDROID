package com.example.seminar2

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class InstaAdapter(private val context: Context) : RecyclerView.Adapter<InstaViewHolder>() {    //더 다양한 디자인의 리사이클러뷰를 사용하고 싶다면 type에 RecyclerView.ViewHolder()(=상위 클래스)를 넣어준다.
    var data = mutableListOf<InstaData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InstaViewHolder {    //viewHolder가 생성되기 전에 호출된다. ViewHolder에 xml을 전송해주는 역할
        val view = LayoutInflater.from(context).inflate(R.layout.item_insta, parent, false)     //item_insta.xml을 view에 연결
        return InstaViewHolder(view)
   }

    override fun getItemCount(): Int {  //배열 data의 크기를 실시간으로 확인한다.
        return data.size
    }

    override fun onBindViewHolder(holder: InstaViewHolder, position: Int) {     //화면에 보여주는 역할
        holder.bind(data[position])     //n번 데이터를 ViewHolder에 묶어서 화면에 보여준다.
    }
}