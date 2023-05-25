package com.example.hastanerandevusistemi.json.repository

import com.example.hastanerandevusistemi.json.entity.SaatEntity


interface SaatRepository {
    suspend fun getAllSaat(gunId: Int): List<SaatEntity>
    suspend fun insertSaat(saat: List<SaatEntity>): List<Long>
    suspend fun updateSaat(gunId : Int, saatId : Int)
}