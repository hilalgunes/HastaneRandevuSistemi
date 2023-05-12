package com.example.hastanerandevusistemi.register

import android.app.Application
import android.util.Log
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

    init {
        Log.i("MYTAG", "init")
    }

    var userDetailsLiveData = MutableLiveData<Array<RegisterEntity>>()

    @Bindable
    val Name = MutableLiveData<String?>()
    @Bindable
    val Surname = MutableLiveData<String?>()
    @Bindable
    val TC = MutableLiveData<String?>()
    @Bindable
    val Password = MutableLiveData<String?>()
    @Bindable
    val Gender = MutableLiveData<String?>()
    @Bindable
    val Email = MutableLiveData<String?>()
    @Bindable
    val Birthday = MutableLiveData<String?>()
    @Bindable
    val Phone = MutableLiveData<String?>()


    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)


    private val _navigateto = MutableLiveData<Boolean>()

    val navigateto: LiveData<Boolean>
        get() = _navigateto

    private val _errorToast = MutableLiveData<Boolean>()

    val errotoast: LiveData<Boolean>
        get() = _errorToast

    private val _errorToastUsername = MutableLiveData<Boolean>()

    val errotoastUsername: LiveData<Boolean>
        get() = _errorToastUsername


    fun register() {
        Log.i("MYTAG", "Inside SUBMIT BUTTON")
        if (Name.value == null || Surname.value == null || TC.value == null || Password.value == null || Gender.value == null || Email.value == null || Birthday.value == null || Phone.value == null) {
            _errorToast.value = true
        } else {
            uiScope.launch {
//            withContext(Dispatchers.IO) {
                val usersNames = repository.getUser(TC.value!!, Password.value!!)
                Log.i("256", usersNames.toString() + "------------------")
                if (usersNames != null) {
                    _errorToastUsername.value = true
                    Log.i("MYTAG", "Inside if Not null")
                } else {
                    Log.i("MYTAG", userDetailsLiveData.value.toString() + "ASDFASDFASDFASDF")
                    Log.i("MYTAG", "OK im in")
                    val name = Name.value!!
                    val surname = Surname.value!!
                    val tc = TC.value!!
                    val gender = Gender.value!!
                    val email = Email.value!!
                    val birthday = Birthday.value!!
                    val phone = Phone.value!!
                    val password = Password.value!!

                    Log.i("MYTAG", "insidi Sumbit")
                    insert(RegisterEntity(0, name, surname, tc, gender, email, birthday, phone, password))
                    Name.value = null
                    Surname.value = null
                    TC.value = null
                    Gender.value = null
                    Email.value = null
                    Birthday.value = null
                    Phone.value = null
                    Password.value = null
                    _navigateto.value = true
                }
            }
        }

    }

    fun doneNavigating() {
        _navigateto.value = false
        Log.i("MYTAG", "Done navigating ")
    }

    fun donetoast() {
        _errorToast.value = false
        Log.i("MYTAG", "Done taoasting ")
    }

    fun donetoastUserName() {
        _errorToast.value = false
        Log.i("MYTAG", "Done taoasting  username")
    }

    private fun insert(user: RegisterEntity): Job = viewModelScope.launch {
        repository.insert(user)
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }
}