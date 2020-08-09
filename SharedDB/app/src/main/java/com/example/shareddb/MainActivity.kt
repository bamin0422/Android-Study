package com.example.shareddb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadData()
    }

    private fun loadData() {
        val pref = getSharedPreferences("pref", 0)
        et_hello.setText(pref.getString("name", "")) // 1번째 인자에서는 저장할 당시의 키 값을 적어줌, 2번째는 키 값에 데이터가 존재하지 않을 경우 대체 데이터를 적어둠.
    }

    override fun onDestroy() { // 해당 액티비티가 종료되는 시점이 다가올 때 호출
        saveData()
        super.onDestroy()
    }

    private fun saveData() {

        val pref = getSharedPreferences("pref", 0)
        val edit = pref.edit()  // 수정모드
        edit.putString("name", et_hello.text.toString()) // 1번째 인자에는 키 값, 2번째 인자에는 실제 담아둘
        edit.apply()
    }
}