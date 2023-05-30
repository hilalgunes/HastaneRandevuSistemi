package com.example.hastanerandevusistemi.appointment

import com.example.hastanerandevusistemi.RequestState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAppointmentUseCase @Inject constructor(var randevuRepository: RandevuRepository) {
    operator fun invoke(userId: Int) = flow {
        emit(RequestState.Loading<List<RandevuEntity>>())
        val result = randevuRepository.getAllAppointment(userId)
        emit(RequestState.Success<List<RandevuEntity>>(result))
    }.catch {
        emit(
            RequestState.Error<List<RandevuEntity>>(
                it.localizedMessage ?: "An unexpected error occurred. Please try again later!"
            )
        )
    }
}