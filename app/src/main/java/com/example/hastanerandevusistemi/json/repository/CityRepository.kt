package com.example.hastanerandevusistemi.json.repository

import com.example.hastanerandevusistemi.json.entity.CityEntity

interface CityRepository {
    suspend fun getAllCity(): List<CityEntity>
    suspend fun insertCity(city: List<CityEntity>): List<Long>
    suspend fun getCount(): Int
}