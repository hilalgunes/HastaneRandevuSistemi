package com.example.hastanerandevusistemi.register

import com.example.hastanerandevusistemi.RequestState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val registerRepository: RegisterRepository) {
    operator fun invoke(tc: String?, password: String) = flow {
        emit(RequestState.Loading<RegisterEntity>())
        val result = registerRepository.getUser(tc, password)
        if (result != null) {
            emit(RequestState.Success(result))
        } else {
            emit(RequestState.Error<RegisterEntity>("Kullanıcı bulunamadı"))
        }
    }.catch {
        emit(
            RequestState.Error<RegisterEntity>(
                it.localizedMessage ?: "An unexpected error occurred. Please try again later!"
            )
        )
    }
}