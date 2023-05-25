package com.example.hastanerandevusistemi.json.useCase.gun

import com.example.hastanerandevusistemi.RequestState
import com.example.hastanerandevusistemi.json.entity.GunEntity
import com.example.hastanerandevusistemi.json.repository.GunRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllGunUseCase @Inject constructor (var gunRepository: GunRepository) {
    operator fun invoke(doctorId: Int) = flow {
        emit(RequestState.Loading<List<GunEntity>>())
        val result = gunRepository.getGun(doctorId)
        emit(RequestState.Success<List<GunEntity>>(result))
    }.catch {
        emit(
            RequestState.Error<List<GunEntity>>(
                it.localizedMessage ?: "An unexpected error occurred. Please try again later!"
            )
        )
    }
}