package com.example.hastanerandevusistemi.json.useCase.poliklinik

import com.example.hastanerandevusistemi.RequestState
import com.example.hastanerandevusistemi.json.entity.PoliklinikEntity
import com.example.hastanerandevusistemi.json.repository.PoliklinikRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPoliklinikUseCase @Inject constructor(var poliklinikRepository: PoliklinikRepository) {
    operator fun invoke(hospitalId: Int) = flow {
        emit(RequestState.Loading<List<PoliklinikEntity>>())
        val result = poliklinikRepository.getAllPoliklinik(hospitalId)
        emit(RequestState.Success<List<PoliklinikEntity>>(result))
    }.catch {
        emit(
            RequestState.Error<List<PoliklinikEntity>>(
                it.localizedMessage ?: "An unexpected error occurred. Please try again later!"
            )
        )
    }
}