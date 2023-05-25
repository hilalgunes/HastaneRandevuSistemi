package com.example.hastanerandevusistemi.json.useCase.doktor

import com.example.hastanerandevusistemi.RequestState
import com.example.hastanerandevusistemi.json.entity.DoktorEntity
import com.example.hastanerandevusistemi.json.repository.DoktorRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class GetAllDoktorUseCase @Inject constructor (
    private val doktorRepository: DoktorRepository
    ) {
    operator fun invoke(departmentId: Int) = flow {
        emit(RequestState.Loading<List<DoktorEntity>>())
        val result = doktorRepository.getDoktor(departmentId)
        emit(RequestState.Success<List<DoktorEntity>>(result))
    }.catch {
        emit(
            RequestState.Error<List<DoktorEntity>>(
                it.localizedMessage ?: "An unexpected error occurred. Please try again later!"
            )
        )
    }
}