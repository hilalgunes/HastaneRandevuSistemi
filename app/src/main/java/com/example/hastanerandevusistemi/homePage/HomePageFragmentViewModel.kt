package com.example.hastanerandevusistemi.homePage

import android.app.Application
import androidx.lifecycle.*
import com.example.hastanerandevusistemi.register.RegisterEntity
import com.example.hastanerandevusistemi.register.RegisterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

class HomePageFragmentViewModel(private val repository: RegisterRepository, application: Application) : AndroidViewModel(application) {

    val kullaniciAdi: LiveData<String?> = MutableLiveData()


    private val _navigateto = MutableLiveData<Boolean>()

    val navigateto: LiveData<Boolean>
        get() = _navigateto

    private val _navigatetoAppointment = MutableLiveData<Boolean>()

    val navigatetoappointment: LiveData<Boolean>
        get() = _navigatetoAppointment

    fun doneNavigating(){
        _navigateto.value=false
    }

    fun profile() {
        _navigateto.value = true
    }

    fun randevual() {
        _navigatetoAppointment.value = true
    }

}