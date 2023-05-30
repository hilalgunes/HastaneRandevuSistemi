package com.example.hastanerandevusistemi.register

import com.example.hastanerandevusistemi.RequestState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SaveUserUseCase @Inject constructor(var repository: RegisterRepository) {
    operator fun invoke(user: Register) = flow {
        emit(RequestState.Loading<Long>())
        val result = repository.insertUser(user.toRegisterEntity())
        emit(RequestState.Success<Long>(result))
    }.catch {
        emit(
            RequestState.Error<Long>(
                it.localizedMessage ?: "An unexpected error occurred. Please try again later!"
            )
        )
    }
}