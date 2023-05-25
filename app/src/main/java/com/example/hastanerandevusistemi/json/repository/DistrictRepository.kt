package com.example.hastanerandevusistemi.json.repository

import com.example.hastanerandevusistemi.json.entity.DistrictEntity

interface DistrictRepository {
    suspend fun getAllDistrict(ilId: Int): List<DistrictEntity>
    suspend fun insertDistrict(district: List<DistrictEntity>): List<Long>
}