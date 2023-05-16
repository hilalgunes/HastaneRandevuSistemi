package com.example.hastanerandevusistemi.login

import android.util.Log
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.hastanerandevusistemi.R
import com.example.hastanerandevusistemi.databinding.FragmentLoginBinding

class LoginFragmentViewModel : ViewModel() {

    private val _navigateToRegisterFragment = MutableLiveData<Boolean>()
    val navigateToRegisterFragment: LiveData<Boolean>
        get() = _navigateToRegisterFragment

    private val _navigateToHomePageFragment = MutableLiveData<Boolean>()
    val navigateToHomePageFragment: LiveData<Boolean>
    get() = _navigateToHomePageFragment

    fun loginButton() {
        _navigateToHomePageFragment.value = true
    }

    fun doneNavigatingToHomePageFragment() {
        _navigateToHomePageFragment.value = false
    }

    fun registerButton() {
        _navigateToRegisterFragment.value = true
    }

    fun doneNavigatingToRegisterFragment() {
        _navigateToRegisterFragment.value = false
    }

}
