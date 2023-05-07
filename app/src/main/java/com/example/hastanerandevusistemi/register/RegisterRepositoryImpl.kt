package com.example.hastanerandevusistemi.register

import androidx.lifecycle.LiveData

class RegisterRepositoryImpl(private val registerDao: RegisterDao) {

    suspend fun addUser(register: RegisterEntity) {
        registerDao.addUser(register)
    }

    suspend fun getAllUser(TC: String, password: String): RegisterEntity {
        return registerDao.getAllUser(TC, password)
    }

    suspend fun getAllUsers(): LiveData<List<RegisterEntity>> {
        return registerDao.getAllUsers()
    }
}