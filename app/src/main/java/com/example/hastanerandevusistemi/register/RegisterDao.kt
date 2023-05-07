package com.example.hastanerandevusistemi.register

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RegisterDao {

    @Insert
    suspend fun addUser(register: RegisterEntity)

    @Query("SELECT * FROM users WHERE tcNO = :tcNo AND password= :password")
    suspend fun getAllUser(tcNo: String, password: String): RegisterEntity

    @Query("SELECT * FROM users")
    fun getAllUsers(): LiveData<List<RegisterEntity>>

}