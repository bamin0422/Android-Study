package com.example.realtimedatabase_firebase

import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.database.*

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    val database = FirebaseDatabase.getInstance()
    val myRef: DatabaseReference = database.getReference("User")

    var cnt: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        btn_add.setOnClickListener {
            addNewUser(User(edit_name.text.toString(), Integer.parseInt(edit_age.text.toString())))
        }

        btn_remove.setOnClickListener {
            removeUser(User(edit_name.text.toString(), Integer.parseInt(edit_age.text.toString())))
        }
    }

    fun addNewUser(user: User) {
        myRef.child("User" + "(${user.name})").setValue(user)

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                Toast.makeText(applicationContext, "${user.name}의 데이터를 등록했습니다.", Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(applicationContext, "값을 읽어오지 못하였습니다ㅠ", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun removeUser(user: User) {
        myRef.child("User" + "(${user.name})").removeValue().addOnSuccessListener{
            Toast.makeText(applicationContext, "${user.name}의 데이터를 삭제하였습니다.", Toast.LENGTH_SHORT).show()
        }
    }
}

