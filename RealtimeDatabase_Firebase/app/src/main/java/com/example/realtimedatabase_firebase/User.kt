package com.example.realtimedatabase_firebase

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class User(
    var name: String? = "",
    var age: Int? = 0
)