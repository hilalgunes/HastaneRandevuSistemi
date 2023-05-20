package com.example.hastanerandevusistemi.appointment

import android.app.Application
import androidx.databinding.Observable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class RandevuViewModel(private val repository: RandevuRepository, application: Application) :
    AndroidViewModel(application), Observable {

    private val makeAppointmentViewModel = MakeAnAppointmentFragmentViewModel(repository, application)

    fun getRandevular(): LiveData<List<RandevuEntity>> {
        return makeAppointmentViewModel.getRandevuList()
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }
}

