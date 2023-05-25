package com.example.hastanerandevusistemi.json.useCase.gun

import com.example.hastanerandevusistemi.RequestState
import com.example.hastanerandevusistemi.json.repository.GunRepository
import com.example.hastanerandevusistemi.model.Gunler
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SaveGunUseCase @Inject constructor (private val gunRepository: GunRepository) {
    operator fun invoke(days: List<Gunler>) = flow {
        emit(RequestState.Loading<Long>())
        val result = gunRepository.insertGun(days.map { it.toGunEntity() })
        emit(RequestState.Success(result))
    }.catch {
        emit(
            RequestState.Error<Long>(
                it.localizedMessage ?: "An unexpected error occurred. Please try again later!"
            )
        )
    }
}