package com.example.hastanerandevusistemi.json

import com.example.hastanerandevusistemi.json.dao.DoktorDao
import com.example.hastanerandevusistemi.json.entity.DoktorEntity
import com.example.hastanerandevusistemi.json.repository.DoktorRepository
import javax.inject.Inject


class DoktorRepositoryImpl @Inject constructor(private val doktorDao: DoktorDao) : DoktorRepository {
    override suspend fun getDoktor(departmentId: Int): List<DoktorEntity> {
        return doktorDao.getDoktor(departmentId)
    }

    override suspend fun insertDoktor(doktor: List<DoktorEntity>): List<Long> {
        return doktorDao.insertDoktor(doktor)
    }
}