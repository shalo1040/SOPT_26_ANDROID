package com.example.review.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.example.review.R
import com.example.review.SigninActivity
import kotlinx.android.synthetic.main.fragment_mypage.*

class MypageFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_mypage, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pref = activity!!.getSharedPreferences("USER_INFO", Context.MODE_PRIVATE)
        tv_id.text = "ID : " + pref.getString("EMAIL", "id")
        tv_pw.text = "PASSWORD : " + pref.getString("PASSWORD", "password")

        btn_signout.setOnClickListener {
            val pref = activity?.getSharedPreferences("LOGIN", Context.MODE_PRIVATE)
            val edit = pref?.edit()
            edit?.putBoolean("isLogin", false)
            edit?.apply()

            Toast.makeText(this.context, "로그아웃 되었습니다", Toast.LENGTH_SHORT).show()
            startActivity(Intent(activity, SigninActivity::class.java))
            activity?.finish()
        }
    }
}
