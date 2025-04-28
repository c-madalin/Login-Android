package com.cst.tema1.data

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository(context: Context) {
    private val userDao = AppDatabase.getDatabase(context).userDao()

    suspend fun registerUser(user: User): Long {
        return withContext(Dispatchers.IO) {
            userDao.insertUser(user)
        }
    }

    suspend fun loginUser(email: String, password: String): User? {
        return withContext(Dispatchers.IO) {
            userDao.getUserByEmailAndPassword(email, password)
        }
    }

    suspend fun checkEmailExists(email: String): Boolean {
        return withContext(Dispatchers.IO) {
            userDao.getUserByEmail(email) != null
        }
    }
}