package com.example.hastanerandevusistemi.appointment

interface RandevuRepository {
    suspend fun getAllAppointment(userId: Int): List<RandevuEntity>
    suspend fun insertAppointment(appointmentEntity: List<RandevuEntity>): List<Long>
}

