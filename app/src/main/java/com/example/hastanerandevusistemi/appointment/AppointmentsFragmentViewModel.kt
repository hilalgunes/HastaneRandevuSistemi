package com.example.hastanerandevusistemi.appointment

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hastanerandevusistemi.RequestState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AppointmentsFragmentViewModel @Inject constructor(application: Application): ViewModel() {

    var myAppointment: MutableLiveData<RequestState<List<RandevuEntity>>?>? = MutableLiveData()
}