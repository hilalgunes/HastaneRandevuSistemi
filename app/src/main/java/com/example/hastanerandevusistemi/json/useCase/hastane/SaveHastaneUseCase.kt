package com.example.hastanerandevusistemi.json.useCase.hastane

import com.example.hastanerandevusistemi.RequestState
import com.example.hastanerandevusistemi.json.repository.HastaneRepository
import com.example.hastanerandevusistemi.model.Hastane
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class SaveHastaneUseCase @Inject constructor(private val hastaneRepository: HastaneRepository) {
    operator fun invoke(hastane: List<Hastane>) = flow {
        emit(RequestState.Loading<Long>())
        val result = hastaneRepository.insertHastane(hastane.map { it.toHastaneEntity() })
        emit(RequestState.Success<Long>(result[0]))
    }.catch {
        emit(
            RequestState.Error<Long>(
                it.localizedMessage ?: "An unexpected error occurred. Please try again later!"
            )
        )
    }
}