package com.example.hastanerandevusistemi.json.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hastanerandevusistemi.json.entity.DistrictEntity

@Dao
interface DistrictDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDistrict(district: List<DistrictEntity>): List<Long>

    @Query("SELECT * FROM district WHERE ilId = :ilId")
    suspend fun getAllDistrict(ilId: Int): List<DistrictEntity>
}