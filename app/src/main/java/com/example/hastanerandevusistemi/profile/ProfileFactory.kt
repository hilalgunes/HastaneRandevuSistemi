package com.example.hastanerandevusistemi.profile

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.hastanerandevusistemi.register.RegisterRepository
import java.lang.IllegalArgumentException


class ProfileFactory (
    private  val repository: RegisterRepository,
    private val application: Application
): ViewModelProvider.Factory{
    @Suppress("Unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ProfileFragmentViewModel::class.java)) {
            return ProfileFragmentViewModel(repository, application) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}
