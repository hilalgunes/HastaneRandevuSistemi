package com.example.hastanerandevusistemi.json

import com.example.hastanerandevusistemi.json.dao.DistrictDao
import com.example.hastanerandevusistemi.json.entity.DistrictEntity
import com.example.hastanerandevusistemi.json.repository.DistrictRepository
import javax.inject.Inject

class DistrictRepositoryImpl @Inject constructor(private val districtDao: DistrictDao) : DistrictRepository {
    override suspend fun getAllDistrict(ilId: Int): List<DistrictEntity> {
        return districtDao.getAllDistrict(ilId)
    }

    override suspend fun insertDistrict(district: List<DistrictEntity>): List<Long> {
        return districtDao.insertDistrict(district)
    }
}