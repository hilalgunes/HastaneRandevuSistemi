package com.example.hastanerandevusistemi.json.useCase.saat

import com.example.hastanerandevusistemi.RequestState
import com.example.hastanerandevusistemi.json.repository.SaatRepository
import com.example.hastanerandevusistemi.model.Saatler
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class SaveSaatUseCase @Inject constructor(private val saatRepository: SaatRepository) {
    operator fun invoke(hour: List<Saatler>) = flow {
        emit(RequestState.Loading<Long>())
        val result = saatRepository.insertSaat(hour.map { it.toSaatEntity() })
        emit(RequestState.Success<Long>(result[0]))
    }.catch {
        emit(
            RequestState.Error<Long>(
                it.localizedMessage ?: "An unexpected error occurred. Please try again later!"
            )
        )
    }
}