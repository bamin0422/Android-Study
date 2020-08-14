package com.example.login_firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_login_email.setOnClickListener {
            SignInWithEmail()
        }
    }

    fun SignInWithEmail() {
        var id = edit_id.text.toString()
        var pw = edit_password.text.toString()

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(id,pw).addOnCompleteListener{
            task -> if(task.isSuccessful)println("Sign In with Email")
        }
    }
}