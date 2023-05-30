package com.example.hastanerandevusistemi.register

import android.app.Application
import android.util.Log
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hastanerandevusistemi.RequestState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RegisterViewModel @Inject constructor(
    private var saveUserUseCase: SaveUserUseCase

) : ViewModel() {


    val registerState : MutableLiveData<Boolean> = MutableLiveData()
    val selectedGender: MutableLiveData<String> = MutableLiveData()

    fun addUser(
        name: String,
        surname: String,
        tc: String,
        gender: String,
        birthday: String,
        email: String,
        phone: String,
         password: String
    ) {
        saveUserUseCase.invoke(
            Register(
                0,
                name,
                surname,
                tc,
                gender,
                birthday,
                email,
                phone,
                password
            )
        ).onEach {
            when (it) {
                is RequestState.Loading -> {
                    Log.d("TAG", "addUser: Loading")
                }

                is RequestState.Success -> {
                    Log.d("TAG", "addUser: ${it.data}")
                    registerState.value = true
                }

                is RequestState.Error -> {
                    Log.d("TAG", "addUser: ${it.message}")
                }
            }
        }.launchIn(viewModelScope)
    }

}