package com.example.hastanerandevusistemi.appointment

import androidx.lifecycle.LiveData

class RandevuRepositoryImpl(private val randevuDao: RandevuDao)  {

    suspend fun insertRandevu(randevu: RandevuEntity) {
        randevuDao.insertRandevu(randevu)
    }


     suspend fun deleteRandevu(randevu: RandevuEntity) {
        randevuDao.deleteRandevu(randevu)
    }

    suspend fun getAllRandevular(): LiveData<List<RandevuEntity>> {
        return randevuDao.getAllRandevular()
    }
}
