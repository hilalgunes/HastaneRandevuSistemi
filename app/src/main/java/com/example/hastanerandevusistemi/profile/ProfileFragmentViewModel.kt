package com.example.hastanerandevusistemi.profile

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.hastanerandevusistemi.BaseViewModel
import com.example.hastanerandevusistemi.MyPreferences
import com.example.hastanerandevusistemi.RequestState
import com.example.hastanerandevusistemi.register.GetUserUseCase
import com.example.hastanerandevusistemi.register.RegisterEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ProfileFragmentViewModel @Inject constructor(
    application: Application,
    private val getUserUseCase: GetUserUseCase
) : BaseViewModel(application) {

    var userInfo: MutableLiveData<RequestState<RegisterEntity?>> = MutableLiveData()


    fun getUserInfo(tc: String, password: String) {
        getUserUseCase.invoke(tc, password).onEach {
            when (it) {
                is RequestState.Loading -> {

                }
                is RequestState.Success -> {
                    userInfo.value = RequestState.Success(it.data)
                }
                is RequestState.Error -> {

                }
            }
        }.launchIn(viewModelScope)
    }
}