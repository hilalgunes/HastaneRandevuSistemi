package com.example.hastanerandevusistemi.json.useCase.saat

import com.example.hastanerandevusistemi.RequestState
import com.example.hastanerandevusistemi.json.entity.SaatEntity
import com.example.hastanerandevusistemi.json.repository.SaatRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class GetAllSaatUseCase @Inject constructor( var saatRepository: SaatRepository) {
    operator fun invoke(dayId: Int) = flow {
        emit(RequestState.Loading<List<SaatEntity>>())
        val result = saatRepository.getAllSaat(dayId)
        emit(RequestState.Success<List<SaatEntity>>(result.filter { it.selected!! }))
    }.catch {
        emit(
            RequestState.Error<List<SaatEntity>>(
                it.localizedMessage ?: "An unexpected error occurred. Please try again later!"
            )
        )
    }
}