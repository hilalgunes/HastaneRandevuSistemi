package com.example.hastanerandevusistemi.profile

import android.app.Application
import android.util.Log
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.hastanerandevusistemi.register.RegisterEntity
import com.example.hastanerandevusistemi.register.RegisterRepository

class ProfileFragmentViewModel(private val repository: RegisterRepository,application: Application)
    :AndroidViewModel(application), Observable{

    val users = repository.users


    @Bindable
    val name = MutableLiveData<String?>()
    @Bindable
    val surname = MutableLiveData<String?>()
    @Bindable
    val tC = MutableLiveData<String?>()
    @Bindable
    var gender = MutableLiveData<String?>()
    @Bindable
    val birthday = MutableLiveData<String?>()
    @Bindable
    val email = MutableLiveData<String?>()
    @Bindable
    val phone = MutableLiveData<String?>()

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }
    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

}