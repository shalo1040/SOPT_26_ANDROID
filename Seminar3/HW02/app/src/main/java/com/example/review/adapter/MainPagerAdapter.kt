package com.example.review.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.review.fragment.BookFragment
import com.example.review.fragment.HomeFragment
import com.example.review.fragment.MypageFragment

class MainPagerAdapter(fm : FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> HomeFragment()
            1 -> BookFragment()
            else -> MypageFragment()
        }
    }

    override fun getCount() = 3
}