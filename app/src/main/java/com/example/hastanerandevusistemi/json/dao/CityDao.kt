package com.example.hastanerandevusistemi.json.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hastanerandevusistemi.json.entity.CityEntity

@Dao
interface CityDao {
    @Query("SELECT * FROM city")
    suspend fun getAllCity(): List<CityEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCity(city: List<CityEntity>): List<Long>

    @Query("SELECT COUNT(*) FROM city")
    suspend fun getCount(): Int
}