package com.example.hastanerandevusistemi.appointment

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MakeAnAppointmentFragmentViewModelFactory(private val application: RandevuRepository, private val repository: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MakeAnAppointmentFragmentViewModel::class.java)) {
            return MakeAnAppointmentFragmentViewModel(application, repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
