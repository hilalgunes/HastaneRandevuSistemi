package com.example.hastanerandevusistemi.json.repository

import com.example.hastanerandevusistemi.json.entity.GunEntity


interface GunRepository {
    suspend fun getGun(doktorId: Int): List<GunEntity>
    suspend fun insertGun(gunEntity: List<GunEntity>): List<Long>
}