package com.example.hastanerandevusistemi.homePage

import android.app.Application
import androidx.lifecycle.*
import com.example.hastanerandevusistemi.register.RegisterEntity
import com.example.hastanerandevusistemi.register.RegisterRepository
import kotlinx.coroutines.launch

class HomePageFragmentViewModel(private val repository: RegisterRepository, application: Application) : AndroidViewModel(application) {

    val kullaniciAdi: MutableLiveData<String?> = MutableLiveData()

    private val _navigateto = MutableLiveData<Boolean>()

    val navigateto: LiveData<Boolean>
        get() = _navigateto

    fun doneNavigating(){
        _navigateto.value=false
    }

    fun profile() {
        _navigateto.value = true
    }
}