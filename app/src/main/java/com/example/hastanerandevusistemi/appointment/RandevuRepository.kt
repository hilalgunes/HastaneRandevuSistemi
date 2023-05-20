package com.example.hastanerandevusistemi.appointment

import androidx.lifecycle.LiveData


class RandevuRepository(private val randevuDao: RandevuDao) {
    suspend fun insertRandevu(randevu: RandevuEntity) {
        randevuDao.insertRandevu(randevu)
    }

    suspend fun deleteRandevu(randevu: RandevuEntity) {
        randevuDao.deleteRandevu(randevu)
    }

    val randevu = randevuDao.getAllRandevular()

    fun getAllRandevular(): LiveData<List<RandevuEntity>> {
        return randevuDao.getAllRandevular()
    }


}

