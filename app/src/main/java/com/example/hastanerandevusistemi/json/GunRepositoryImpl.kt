package com.example.hastanerandevusistemi.json

import com.example.hastanerandevusistemi.json.dao.GunDao
import com.example.hastanerandevusistemi.json.entity.GunEntity
import com.example.hastanerandevusistemi.json.repository.GunRepository
import javax.inject.Inject

class GunRepositoryImpl @Inject constructor (private val gunDao: GunDao): GunRepository {
    override suspend fun getGun(doktorId: Int): List<GunEntity> {
        return gunDao.getGun(doktorId)
    }

    override suspend fun insertGun(gunEntity: List<GunEntity>): List<Long> {
        return gunDao.insertGun(gunEntity)
    }

}