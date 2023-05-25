package com.example.hastanerandevusistemi.json

import com.example.hastanerandevusistemi.json.dao.HastaneDao
import com.example.hastanerandevusistemi.json.entity.HastaneEntity
import com.example.hastanerandevusistemi.json.repository.HastaneRepository
import javax.inject.Inject

class HastaneRepositoryImpl @Inject constructor (private val hastaneDao: HastaneDao): HastaneRepository {
    override suspend fun getAllHastane(ilçeId: Int): List<HastaneEntity> {
        return hastaneDao.getAllHastane(ilçeId)
    }

    override suspend fun insertHastane(hospital: List<HastaneEntity>): List<Long> {
        return hastaneDao.insertHastane(hospital)
    }
}