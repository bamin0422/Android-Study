package com.example.fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var a : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_fragment1.setOnClickListener {
            a++
            Log.d("Fragment", a.toString())
            setFrag(0)
        }

        btn_fragment2.setOnClickListener {
            Log.d("Fragment2", "Fragment2 is activated!")
            setFrag(1)
        }

        btn_fragment3.setOnClickListener {
            Log.d("Fragment3", "Fragment3 is activated!")
            setFrag(2)
        }
    }

    private fun setFrag(fragNum : Int) {
        val ft = supportFragmentManager.beginTransaction()
        when(fragNum){
            0 -> ft.replace(R.id.main_frame, Fragment1()).commit()
            1 -> ft.replace(R.id.main_frame, Fragment2()).commit()
            2 -> ft.replace(R.id.main_frame, Fragment3()).commit()
        }
    }

}