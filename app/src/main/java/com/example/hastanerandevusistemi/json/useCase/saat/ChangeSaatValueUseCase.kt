package com.example.hastanerandevusistemi.json.useCase.saat

import com.example.hastanerandevusistemi.RequestState
import com.example.hastanerandevusistemi.appointment.RandevuDao
import com.example.hastanerandevusistemi.appointment.RandevuEntity
import com.example.hastanerandevusistemi.json.repository.SaatRepository
import com.example.hastanerandevusistemi.model.Randevu
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class ChangeSaatValueUseCase @Inject constructor(var saatRepository: SaatRepository) {
    operator fun invoke(randevu : Randevu) = flow {
        emit(RequestState.Loading<Any>())
        val result = saatRepository.updateSaat(randevu.dayId!!, randevu.hourId!!)
        emit(RequestState.Success<Any>(result))
    }.catch {
        emit(
            RequestState.Error<Any>(
                it.localizedMessage ?: "An unexpected error occurred. Please try again later!"
            )
        )
    }
}