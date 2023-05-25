package com.example.hastanerandevusistemi.json.repository

import com.example.hastanerandevusistemi.json.entity.DoktorEntity


interface DoktorRepository {
    suspend fun getDoktor(departmentId:Int): List<DoktorEntity>
    suspend fun insertDoktor(doktor: List<DoktorEntity>): List<Long>
}