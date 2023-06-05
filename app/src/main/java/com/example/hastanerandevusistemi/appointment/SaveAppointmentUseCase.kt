package com.example.hastanerandevusistemi.appointment

import com.example.hastanerandevusistemi.RequestState
import com.example.hastanerandevusistemi.model.Randevu
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SaveAppointmentUseCase @Inject constructor(var randevuRepository: RandevuRepository) {
    operator fun invoke(appointmentEntity: List<Randevu>) = flow {
        emit(RequestState.Loading<Long>())
        val result =
            randevuRepository.insertAppointment(appointmentEntity.map { it.toAppointmentEntity() })
        emit(RequestState.Success<Long>(result[0]))
    }.catch {
        emit(
            RequestState.Error<Long>(
                it.localizedMessage ?: "An unexpected error occurred. Please try again later!"
            )
        )
    }
}