package com.example.hastanerandevusistemi.appointment


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RandevuDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAppointment(randevuEntity: List<RandevuEntity>): List<Long>

    @Query("SELECT * FROM randevu WHERE userId = :userId")
    suspend fun getAllAppointment(userId: Int): List<RandevuEntity>
}