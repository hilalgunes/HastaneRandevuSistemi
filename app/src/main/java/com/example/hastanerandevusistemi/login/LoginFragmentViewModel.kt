package com.example.hastanerandevusistemi.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hastanerandevusistemi.databinding.FragmentLoginBinding

class LoginFragmentViewModel : ViewModel() {

    private val _navigateToHomeFragment = MutableLiveData<Boolean>()
    val navigateToHomeFragment: LiveData<Boolean>
        get() = _navigateToHomeFragment

    fun registerButton() {
        // Register butonuna tıklandığında yapılacak işlemler

        // Geçiş işlemini başlatmak için _navigatetoUserDetails değerini true olarak ayarlayın
        _navigateToHomeFragment.value = true

    }

    fun loginButton() {
        _navigateToHomeFragment.value = true
    }

    fun doneNavigatingUserDetails() {
        // Geçiş işlemi tamamlandığında _navigatetoUserDetails değerini false olarak ayarlayın
        _navigateToHomeFragment.value = false
    }
}
