package com.example.review.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.review.R
import com.example.review.SpacesItemDecoration
import com.example.review.adapter.InstaAdapter
import com.example.review.data.InstaData
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {
    val data = mutableListOf<InstaData>()
    lateinit var instaAdapter : InstaAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        instaAdapter = InstaAdapter(view.context)
        rv_home.adapter = instaAdapter
        rv_home.addItemDecoration(SpacesItemDecoration(30))
        loadData()
    }

    fun loadData() {
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
