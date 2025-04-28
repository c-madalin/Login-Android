package com.cst.tema1.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val email: String,
    val password: String,
    val firstName: String? = null,
    val lastName: String? = null,
    val age: Int? = null
)