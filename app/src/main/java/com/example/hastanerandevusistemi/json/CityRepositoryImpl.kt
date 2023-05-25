package com.example.hastanerandevusistemi.json

import com.example.hastanerandevusistemi.json.dao.CityDao
import com.example.hastanerandevusistemi.json.entity.CityEntity
import com.example.hastanerandevusistemi.json.repository.CityRepository
import javax.inject.Inject


class CityRepositoryImpl @Inject constructor(private val cityDao: CityDao) : CityRepository {
    override suspend fun getAllCity(): List<CityEntity> {
        return cityDao.getAllCity()
    }

    override suspend fun insertCity(city: List<CityEntity>): List<Long> {
        return cityDao.insertCity(city)
    }

    override suspend fun getCount(): Int {
        return cityDao.getCount()
    }
}
