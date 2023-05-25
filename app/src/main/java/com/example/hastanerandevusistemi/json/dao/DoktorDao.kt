package com.example.hastanerandevusistemi.json.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hastanerandevusistemi.json.entity.DoktorEntity

@Dao
interface DoktorDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDoktor(doctors: List<DoktorEntity>): List<Long>

    @Query("SELECT * FROM doktor WHERE poliklinikId = :departmentId")
    suspend fun getDoktor(departmentId: Int): List<DoktorEntity>
}