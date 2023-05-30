package com.example.hastanerandevusistemi.homePage

import android.app.Application
import androidx.lifecycle.*
import com.example.hastanerandevusistemi.BaseViewModel
import com.example.hastanerandevusistemi.register.RegisterEntity
import com.example.hastanerandevusistemi.register.RegisterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomePageFragmentViewModel @Inject constructor(
    application: Application,
) : BaseViewModel(application) {
}