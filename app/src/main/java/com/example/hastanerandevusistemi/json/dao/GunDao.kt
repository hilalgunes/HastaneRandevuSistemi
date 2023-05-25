package com.example.hastanerandevusistemi.json.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hastanerandevusistemi.json.entity.GunEntity


@Dao
interface GunDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGun(gunEntity: List<GunEntity>): List<Long>

    @Query("SELECT * FROM gun WHERE doktorId = :doctorId")
    suspend fun getGun(doctorId: Int): List<GunEntity>
}