package com.example.hastanerandevusistemi.register

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RegisterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(register: RegisterEntity) : Long

    @Query("SELECT * FROM Register_users_table")
    suspend fun getAllUser(): List<RegisterEntity>


    @Query("SELECT * FROM Register_users_table WHERE tcNo = :tcNo AND password = :password")
    suspend fun getUser(tcNo: String?, password: String): RegisterEntity

    @Query("SELECT * FROM Register_users_table WHERE userId = :userid")
    suspend fun getUserid(userid: Int): RegisterEntity

}