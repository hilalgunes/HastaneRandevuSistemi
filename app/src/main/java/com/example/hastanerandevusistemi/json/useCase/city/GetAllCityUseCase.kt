package com.example.hastanerandevusistemi.json.useCase.city

import com.example.hastanerandevusistemi.RequestState
import com.example.hastanerandevusistemi.json.entity.CityEntity
import com.example.hastanerandevusistemi.json.repository.CityRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllCityUseCase @Inject constructor (
    private var repository: CityRepository
) {
    operator fun invoke() = flow {
        emit(RequestState.Loading<List<CityEntity>>())
        val result = repository.getAllCity()
        emit(RequestState.Success<List<CityEntity>>(result))
    }.catch {
        emit(
            RequestState.Error<List<CityEntity>>(
                it.localizedMessage ?: "An unexpected error occurred. Please try again later!"
            )
        )
    }
}