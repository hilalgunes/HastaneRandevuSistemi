package com.example.hastanerandevusistemi.register


interface RegisterRepository {
    suspend fun insertUser(user: RegisterEntity): Long
    suspend fun getAllUser(): List<RegisterEntity>
    suspend fun getUserid(id: Int): RegisterEntity
    suspend fun getUser(tc: String?, password: String): RegisterEntity
}