package com.example.hastanerandevusistemi.json.repository

import com.example.hastanerandevusistemi.json.entity.PoliklinikEntity

interface PoliklinikRepository {
    suspend fun getAllPoliklinik(hastaneId: Int): List<PoliklinikEntity>
    suspend fun insertPoliklinik(poliklinikEntity: List<PoliklinikEntity>): List<Long>
}
