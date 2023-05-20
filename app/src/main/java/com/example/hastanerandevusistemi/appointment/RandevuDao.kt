package com.example.hastanerandevusistemi.appointment

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RandevuDao {

    @Insert
    suspend fun insertRandevu(randevu: RandevuEntity)

    @Delete
    suspend fun deleteRandevu(randevu: RandevuEntity)

    @Query("SELECT * FROM randevu_table")
    fun getAllRandevular(): LiveData<List<RandevuEntity>>

}