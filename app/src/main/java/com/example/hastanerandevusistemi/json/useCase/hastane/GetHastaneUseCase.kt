package com.example.hastanerandevusistemi.json.useCase.hastane

import com.example.hastanerandevusistemi.RequestState
import com.example.hastanerandevusistemi.json.entity.HastaneEntity
import com.example.hastanerandevusistemi.json.repository.HastaneRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class GetHastaneUseCase @Inject constructor(var hastaneRepository: HastaneRepository) {
    operator fun invoke(ilçeId: Int) = flow {
        emit(RequestState.Loading<List<HastaneEntity>>())
        val result = hastaneRepository.getAllHastane(ilçeId)
        emit(RequestState.Success<List<HastaneEntity>>(result))
    }.catch {
        emit(
            RequestState.Error<List<HastaneEntity>>(
                it.localizedMessage ?: "An unexpected error occurred. Please try again later!"
            )
        )
    }
}