package com.example.hastanerandevusistemi.register

import androidx.lifecycle.LiveData

interface RegisterRepository {


    suspend fun insert(user: RegisterEntity)

    suspend fun getAllUsers(): LiveData<List<RegisterEntity>>

    suspend fun getUser(tcNo: String, password: String): RegisterEntity?

}