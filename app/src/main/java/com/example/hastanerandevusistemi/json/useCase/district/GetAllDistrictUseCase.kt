package com.example.hastanerandevusistemi.json.useCase.district

import com.example.hastanerandevusistemi.RequestState
import com.example.hastanerandevusistemi.json.entity.DistrictEntity
import com.example.hastanerandevusistemi.json.repository.DistrictRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class GetAllDistrictUseCase @Inject constructor(
    private val districtRepository: DistrictRepository
) {
    operator fun invoke(ilId: Int) = flow {
        emit(RequestState.Loading<List<DistrictEntity>>())
        val result = districtRepository.getAllDistrict(ilId)
        emit(RequestState.Success<List<DistrictEntity>>(result))
    }.catch {
        emit(
            RequestState.Error<List<DistrictEntity>>(
                it.localizedMessage ?: "An unexpected error occurred. Please try again later!"
            )
        )
    }
}