package com.example.hastanerandevusistemi

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

open class BaseViewModel
    (application: Application) :
    AndroidViewModel(application) {
    protected val context
        get() = getApplication<Application>()
}