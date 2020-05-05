package com.example.seminar2

import android.graphics.drawable.Drawable
import android.icu.lang.UCharacter
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {
    lateinit var instaAdapter: InstaAdapter
    val data = mutableListOf<InstaData>()       //원하는 값을 넣을 배열
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {       //onCreateView() 바로 다음에 실행
        super.onViewCreated(view, savedInstanceState)

        rv_home.addItemDecoration(SpacesItemDecoration(50));

        //구분선
        val dividerItemDecoration = DividerItemDecoration(view.context, 1);
        rv_home.addItemDecoration(dividerItemDecoration);

        instaAdapter = InstaAdapter(view.context)       //액티비티의 context
        rv_home.adapter = instaAdapter              //xml에 내가 만든 어뎁터 연결
        loadData()  //데이터를 임의로 생성하고 어댑터에 전달
    }

    private fun loadData() {
        data.apply {
            add(
                InstaData(
                    userName = "김다은",
                    img_profile = "https://images.unsplash.com/photo-1587583484084-8f9dce72d4da?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60",
                    img_contents = "https://images.unsplash.com/photo-1587578075208-f206676d9860?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60"
                )
            )
            add(
                InstaData(
                    userName = "이지우",
                    img_profile = "https://images.unsplash.com/photo-1587567657174-df73913e5604?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60",
                    img_contents = "https://images.unsplash.com/photo-1587657565520-6c0c23cf68c7?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60"
                )
            )
            add(
                InstaData(
                    userName = "윤민지",
                    img_profile = "https://images.unsplash.com/photo-1555279868-ef49a70cfabd?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60",
                    img_contents = "https://images.unsplash.com/photo-1587687631170-9d020578b077?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60"
                )
            )
        }
        instaAdapter.data = data
        instaAdapter.notifyDataSetChanged()
    }
}
