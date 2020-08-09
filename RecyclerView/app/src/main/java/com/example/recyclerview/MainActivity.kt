package com.example.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val profileList = arrayListOf(
            Profiles(R.drawable.userman, "대창너무조아", 23, "컴퓨터비전전문가"),
            Profiles(R.drawable.userwoman, "내장너무조아", 22, "비전전문가"),
            Profiles(R.drawable.userman, "막창너무조아", 26, "안드로이드 앱 개발자"),
            Profiles(R.drawable.userman, "곱창너무조아", 29, "IOS 앱 개발자"),
            Profiles(R.drawable.userwoman, "염통너무조아", 22, "플러터 앱 개발자"),
            Profiles(R.drawable.userwoman, "고기너무조아", 21, "리액트 앱 개발자"),
            Profiles(R.drawable.userwoman, "삼겹너무조아", 24, "하이브리드 앱 개발자"),
            Profiles(R.drawable.userwoman, "한우너무조아", 26, "웹앱 개발자"),
            Profiles(R.drawable.userman, "살치너무조아", 28, "반응형 웹 개발자"),
            Profiles(R.drawable.userman, "새우너무조아", 20, "유니티 앱 개발")
        )

        rv_profile.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_profile.setHasFixedSize(true)

        rv_profile.adapter = ProfileAdapter(profileList)
    }
}