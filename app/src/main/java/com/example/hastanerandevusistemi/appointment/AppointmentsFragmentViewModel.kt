package com.example.hastanerandevusistemi.appointment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hastanerandevusistemi.RequestState
import com.example.hastanerandevusistemi.register.GetUserUseCase
import com.example.hastanerandevusistemi.register.RegisterEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AppointmentsFragmentViewModel @Inject constructor(
    private  var getUserUseCase: GetUserUseCase,
    private var getAppointmentUseCase: GetAppointmentUseCase
): ViewModel() {

    var myAppointment: MutableLiveData<RequestState<List<RandevuEntity>>?>? = MutableLiveData()
    var userId: MutableLiveData<RequestState<RegisterEntity?>> = MutableLiveData()

    fun getUserId(tc: String?, password: String) {
        getUserUseCase.invoke(tc, password).onEach {
            when (it) {
                is RequestState.Loading -> {
                    Log.d("TAG", "getUserId: Loading")
                }
                is RequestState.Success -> {
                    userId.value = RequestState.Success(it.data)
                }
                is RequestState.Error -> {
                    Log.d("TAG", "getUserId: Error")
                }
            }
        }.launchIn(viewModelScope)

    }

    fun getAppointmentByUser(userId: Int) {
        getAppointmentUseCase.invoke(userId).onEach {
            when (it) {
                is RequestState.Loading -> {
                    Log.d("TAG", "getAppointmentByUser: Loading")
                }
                is RequestState.Success -> {
                    myAppointment?.value = it
                }
                is RequestState.Error -> {
                    Log.d("TAG", "getAppointmentByUser: Error")
                }
            }
        }.launchIn(viewModelScope)
    }
}