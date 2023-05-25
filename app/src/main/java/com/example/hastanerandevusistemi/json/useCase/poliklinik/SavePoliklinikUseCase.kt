package com.example.hastanerandevusistemi.json.useCase.poliklinik

import com.example.hastanerandevusistemi.RequestState
import com.example.hastanerandevusistemi.json.repository.PoliklinikRepository
import com.example.hastanerandevusistemi.model.Polikinlik
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class SavePoliklinikUseCase @Inject constructor(private val poliklinikRepository: PoliklinikRepository) {
    operator fun invoke(poliklinikEntity: List<Polikinlik>) = flow {
        emit(RequestState.Loading<Long>())
        val result =
            poliklinikRepository.insertPoliklinik(poliklinikEntity.map { it.toPoliklinikEntity() })
        emit(RequestState.Success<Long>(result[0]))
    }.catch {
        emit(
            RequestState.Error<Long>(
                it.localizedMessage ?: "An unexpected error occurred. Please try again later!"
            )
        )
    }
}