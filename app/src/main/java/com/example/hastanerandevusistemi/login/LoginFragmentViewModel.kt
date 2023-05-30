package com.example.hastanerandevusistemi.login

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.hastanerandevusistemi.BaseViewModel
import com.example.hastanerandevusistemi.RequestState
import com.example.hastanerandevusistemi.register.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class LoginFragmentViewModel @Inject constructor(
    application: Application,
    var getUserUseCase: GetUserUseCase
) : BaseViewModel(application) {

    var loginState : MutableLiveData<Boolean> = MutableLiveData()


    fun getUserByTcAndPassword(tc: String, password: String) {
        getUserUseCase.invoke(tc, password).onEach {
            when (it) {
                is RequestState.Loading -> {
                    Log.d("TAG", "getUser: Loading")
                }

                is RequestState.Success -> {
                    Log.d("TAG", "getUser: ${it.data}")
                    loginState.value = true
                }

                is RequestState.Error -> {
                    Log.d("TAG", "getUser: ${it.message}")
                    loginState.value = false
                }
            }
        }.launchIn(viewModelScope)
    }


}
