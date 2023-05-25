package com.example.hastanerandevusistemi.json

import com.example.hastanerandevusistemi.json.dao.PoliklinikDao
import com.example.hastanerandevusistemi.json.entity.PoliklinikEntity
import com.example.hastanerandevusistemi.json.repository.PoliklinikRepository
import javax.inject.Inject

class PoliklinikRepositoryImpl @Inject constructor (private val poliklinikDao: PoliklinikDao): PoliklinikRepository {
    override suspend fun getAllPoliklinik(hastaneId: Int): List<PoliklinikEntity> {
        return poliklinikDao.getAllPoliklinik(hastaneId)
    }

    override suspend fun insertPoliklinik(poliklinikEntity: List<PoliklinikEntity>): List<Long> {
        return poliklinikDao.insertPoliklinik(poliklinikEntity)
    }
}