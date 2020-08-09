package com.example.listview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var UserList = arrayListOf<User>(
        User(R.drawable.ic_launcher_foreground, "대창너무조아", "23", "ㅎㅇㅎㅇ")
        ,User(R.drawable.ic_launcher_foreground, "내장너무조아", "23", "ㅎㅇㅎㅇ")
        ,User(R.drawable.ic_launcher_foreground, "막창너무조아", "23", "ㅎㅇㅎㅇ")
        ,User(R.drawable.ic_launcher_foreground, "곱창너무조아", "23", "ㅎㅇㅎㅇ")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val item = arrayOf("Apple", "Pear", "Watermelon", "Melon")
//        listView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, item)


        val Adapter = UserAdapter(this, UserList)
        listView.adapter = Adapter

        listView.onItemClickListener = AdapterView.OnItemClickListener{parent, view, position, id ->
            val selectItem = parent.getItemAtPosition(position) as User
            Toast.makeText(this, selectItem.name, Toast.LENGTH_SHORT).show()
        }
    }
}