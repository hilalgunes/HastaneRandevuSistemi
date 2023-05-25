package com.example.hastanerandevusistemi.json

import com.example.hastanerandevusistemi.json.dao.SaatDao
import com.example.hastanerandevusistemi.json.entity.SaatEntity
import com.example.hastanerandevusistemi.json.repository.SaatRepository
import javax.inject.Inject

class SaatRepositoryImpl @Inject constructor(private val saatDao: SaatDao): SaatRepository {
    override suspend fun getAllSaat(gunId: Int): List<SaatEntity> {
        return saatDao.getAllSaat(gunId)
    }

    override suspend fun insertSaat(saat: List<SaatEntity>): List<Long> {
        return saatDao.insertSaat(saat)
    }

    override suspend fun updateSaat(gunId: Int, saatId: Int) {
        return saatDao.updateSaat(gunId, saatId)
    }
}