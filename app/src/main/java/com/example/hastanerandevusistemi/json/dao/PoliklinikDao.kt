package com.example.hastanerandevusistemi.json.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hastanerandevusistemi.json.entity.PoliklinikEntity

@Dao
interface PoliklinikDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPoliklinik(poliklinikEntityList: List<PoliklinikEntity>): List<Long>

    @Query("SELECT * FROM poliklinik WHERE hastaneId = :hastaneId")
    suspend fun getAllPoliklinik(hastaneId: Int): List<PoliklinikEntity>
}