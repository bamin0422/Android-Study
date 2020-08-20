package com.example.firestore_firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val db = FirebaseFirestore.getInstance()
    var s = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val user = User("Dae", "Chang", 1998)

    }

    fun addNewUser(user: User){

        db.collection("users").add(user).addOnSuccessListener {
            tv.setText("name : ${user.first}${user.last},\nborn : ${user.born}")
            Toast.makeText(applicationContext, "Hello, ${user.first}${user.last}!!", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener{
            tv.setText("Error : Can't Found user")
            Toast.makeText(applicationContext, "Sorry, I can't", Toast.LENGTH_SHORT).show()
        }

    }

    fun readUser(user: User){
        db.collection("users").get().addOnSuccessListener {
            result -> for(document in result){
            s += document.toString()
            s += "\n"
        }
        }

        tv.text =
    }
}