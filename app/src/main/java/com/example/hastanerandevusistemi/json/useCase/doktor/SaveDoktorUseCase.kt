package com.example.hastanerandevusistemi.json.useCase.doktor

import com.example.hastanerandevusistemi.RequestState
import com.example.hastanerandevusistemi.json.repository.DoktorRepository
import com.example.hastanerandevusistemi.model.Doktor
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class SaveDoktorUseCase @Inject constructor (private val doktorRepository: DoktorRepository) {
    operator fun invoke(doctors: List<Doktor>) = flow {
        emit(RequestState.Loading<Long>())
        val result = doktorRepository.insertDoktor(doctors.map { it.toDoktorEntity() })
        emit(RequestState.Success<Long>(result[0]))
    }.catch {
        emit(
            RequestState.Error<Long>(
                it.localizedMessage ?: "An unexpected error occurred. Please try again later!"
            )
        )
    }
}