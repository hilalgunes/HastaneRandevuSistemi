package com.example.hastanerandevusistemi.login

import android.app.Application
import android.util.Log
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hastanerandevusistemi.register.RegisterRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class LoginFragmentViewModel(private val repository: RegisterRepository, application: Application) :
    AndroidViewModel(application), Observable {


    @Bindable
    val tC = MutableLiveData<String?>()
    @Bindable
    val password = MutableLiveData<String?>()

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _errorToast = MutableLiveData<Boolean>()

    val errotoast: LiveData<Boolean>
        get() = _errorToast

    private val _errorToastUser = MutableLiveData<Boolean>()

    val errotoastUser: LiveData<Boolean>
        get() = _errorToastUser

    private val _errorToastInvalidPassword = MutableLiveData<Boolean>()

    val errorToastInvalidPassword: LiveData<Boolean>
        get() = _errorToastInvalidPassword

    private val _navigateToLogin= MutableLiveData<Boolean>()

    val navigateToLogin: LiveData<Boolean>
    get() = _navigateToLogin

    private val _navigatetoRegister = MutableLiveData<Boolean>()

    val navigatetoRegister: LiveData<Boolean>
        get() = _navigatetoRegister

    fun signup() {
        _navigatetoRegister.value = true
    }
    fun loginButton() {
        if (tC.value == null || password.value == null ) {
            _errorToast.value = true
        } else {
            uiScope.launch {
                val usersNames = repository.getUser(tC.value!!, password.value!!)
                if (usersNames != null) {
                    if(usersNames.password == password.value){
                        tC.value = null
                        password.value = null
                        _navigateToLogin.value = true
                    }else{
                        _errorToastInvalidPassword.value = true
                    }
                } else {
                    _errorToastUser.value = true
                }
            }
        }
    }

    fun doneNavigating() {
        _navigateToLogin.value = false
    }

    fun donetoast() {
        _errorToast.value = false
        Log.i("MYTAG", "Done taoasting ")
    }

    fun donetoastErrorUsername() {
        _errorToastUser .value = false
        Log.i("MYTAG", "Done taoasting ")
    }

    fun donetoastInvalidPassword() {
        _errorToastInvalidPassword .value = false
        Log.i("MYTAG", "Done taoasting ")
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }
    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

}
