package com.example.hastanerandevusistemi.json.useCase.city

import com.example.hastanerandevusistemi.RequestState
import com.example.hastanerandevusistemi.json.repository.CityRepository
import com.example.hastanerandevusistemi.model.City
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SaveCityUseCase @Inject constructor(
    private val cityRepository: CityRepository
) {
    operator fun invoke(city: List<City>) = flow {
        emit(RequestState.Loading<Long>())
        val result = cityRepository.insertCity(city.map { it.toCityEntity() })
        emit(RequestState.Success<Long>(result[0]))
    }.catch {
        emit(
            RequestState.Error<Long>(
                it.localizedMessage ?: "An unexpected error occurred. Please try again later!"
            )
        )
    }
}