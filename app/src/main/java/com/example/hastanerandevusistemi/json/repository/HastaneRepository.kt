package com.example.hastanerandevusistemi.json.repository

import com.example.hastanerandevusistemi.json.entity.HastaneEntity

interface HastaneRepository {
    suspend fun getAllHastane(ilçeId: Int): List<HastaneEntity>
    suspend fun insertHastane(hospital: List<HastaneEntity>): List<Long>
}