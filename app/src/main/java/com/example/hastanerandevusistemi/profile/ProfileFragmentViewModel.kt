package com.example.hastanerandevusistemi.profile

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.example.hastanerandevusistemi.register.RegisterRepository

class ProfileFragmentViewModel(private val repository: RegisterRepository,application: Application):AndroidViewModel(application){

    val users = repository.users
    init {
        Log.i("MYTAG","inside_users_Lisrt_init")
    }

}