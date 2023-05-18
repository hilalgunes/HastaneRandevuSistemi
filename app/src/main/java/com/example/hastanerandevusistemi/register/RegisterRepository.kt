package com.example.hastanerandevusistemi.register


class RegisterRepository(private val dao: RegisterDao) {
    val users = dao.getAllUsers()

    suspend fun insert(user: RegisterEntity) {
        return dao.addUser(user)
    }


    suspend fun getUser(tcNo: String, password: String): RegisterEntity?{
        return dao.getUser(tcNo,password)
    }

}