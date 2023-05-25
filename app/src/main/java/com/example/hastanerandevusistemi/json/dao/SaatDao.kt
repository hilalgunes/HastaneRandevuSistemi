package com.example.hastanerandevusistemi.json.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hastanerandevusistemi.json.entity.SaatEntity

@Dao
interface SaatDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSaat(saatEntity: List<SaatEntity>): List<Long>

    @Query("SELECT * FROM saat WHERE gunId = :gunId")
    suspend fun getAllSaat(gunId: Int): List<SaatEntity>

    @Query("UPDATE saat SET selected = 0 WHERE gunId = :gunId AND value = :saatId")
    suspend fun updateSaat(gunId: Int, saatId: Int)
}