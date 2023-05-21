package com.example.hastanerandevusistemi.register

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class RegisterViewModel(private val repository: RegisterRepository, application: Application) :
    AndroidViewModel(application), Observable {


    @Bindable
    val name = MutableLiveData<String?>()
    @Bindable
    val surname = MutableLiveData<String?>()
    @Bindable
    val tC = MutableLiveData<String?>()
    @Bindable
    val gender = MutableLiveData<String?>()
    @Bindable
    val birthday = MutableLiveData<String?>()
    @Bindable
    val email = MutableLiveData<String?>()
    @Bindable
    val phone = MutableLiveData<String?>()
    @Bindable
    val password = MutableLiveData<String?>()


    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _errorToast = MutableLiveData<Boolean>()

    val errotoast: LiveData<Boolean>
        get() = _errorToast

    private val _errorToastUsername = MutableLiveData<Boolean>()

    val errotoastUsername: LiveData<Boolean>
        get() = _errorToastUsername


    private val _navigateToLoginFragment = MutableLiveData<Boolean>()

    val navigateToLoginFragment: LiveData<Boolean>
        get() = _navigateToLoginFragment

    fun login() {
        _navigateToLoginFragment.value = true
    }

    fun registerButton() {
        Log.i("MYTAG", "Inside SUBMIT BUTTON")
        if (name.value.isNullOrEmpty() || surname.value.isNullOrEmpty() || tC.value.isNullOrEmpty() ||
            gender.value.isNullOrEmpty()  ||  birthday.value.isNullOrEmpty()  || email.value.isNullOrEmpty()||
            phone.value.isNullOrEmpty() || password.value.isNullOrEmpty()
        ) {
            _errorToast.value = true
        } else {
            uiScope.launch {
                val usersNames = repository.getUser(tC.value!!, password.value!!)
                Log.i("256", usersNames.toString() + "------------------")
                if (usersNames != null) {
                    _errorToastUsername.value = true
                    Log.i("MYTAG", "Inside if Not null")
                } else {
                    Log.i("MYTAG", "OK im in")
                    val Name = name.value!!
                    val Surname = surname.value!!
                    val tc = tC.value!!
                    val Gender = gender.value!!
                    val Birthday = birthday.value!!
                    val Email = email.value!!
                    val Phone = phone.value!!
                    val Password = password.value!!

                    Log.i("MYTAG", "inside Submit")
                    insert(RegisterEntity(0, Name, Surname, tc, Gender,Birthday, Email, Phone, Password))
                    name.value = null
                    surname.value = null
                    tC.value = null
                    gender.value = null
                    birthday.value = null
                    email.value = null
                    phone.value = null
                    password.value = null
                    _navigateToLoginFragment.value = true
                }
            }
        }
    }

    fun doneNavigating() {
        _navigateToLoginFragment.value = false
        Log.i("MYTAG", "Done navigating ")
    }

    fun donetoast() {
        _errorToast.value = false
        Log.i("MYTAG", "Done taoasting ")
    }

    private fun insert(user: RegisterEntity): Job = viewModelScope.launch {
        repository.insert(user)
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

}