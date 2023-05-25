package com.example.hastanerandevusistemi.appointment

import androidx.lifecycle.LiveData
import javax.inject.Inject


class RandevuRepositoryImpl @Inject constructor (var randevuDao: RandevuDao) : RandevuRepository {
        override suspend fun getAllAppointment(userId: Int): List<RandevuEntity> {
            return randevuDao.getAllAppointment(userId)
        }

        override suspend fun insertAppointment(appointmentEntity: List<RandevuEntity>): List<Long> {
            return randevuDao.insertAppointment(appointmentEntity)
        }
    }