package com.example.hastanerandevusistemi.json.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hastanerandevusistemi.json.entity.HastaneEntity

@Dao
interface HastaneDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHastane(hospitalEntity: List<HastaneEntity>): List<Long>

    @Query("SELECT * FROM hastane WHERE ilceId = :ilçeId")
    suspend fun getAllHastane(ilçeId: Int): List<HastaneEntity>
}