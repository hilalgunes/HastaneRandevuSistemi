package com.example.hastanerandevusistemi.register


import javax.inject.Inject

class RegisterRepositoryImpl @Inject constructor(var registerDao: RegisterDao) : RegisterRepository {

    override suspend fun insertUser(register: RegisterEntity): Long {
        return registerDao.insertUser(register)
    }

    override suspend fun getAllUser(): List<RegisterEntity> {
        return registerDao.getAllUser()
    }

    override suspend fun getUserid(id: Int): RegisterEntity {
        return registerDao.getUserid(id)
    }
    override suspend fun getUser(tc: String?, password: String): RegisterEntity {
        return registerDao.getUser(tc, password)
    }

}