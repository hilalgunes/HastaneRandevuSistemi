package com.example.hastanerandevusistemi.appointment
import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.hastanerandevusistemi.BaseViewModel
import com.example.hastanerandevusistemi.RequestState
import com.example.hastanerandevusistemi.json.entity.*
import com.example.hastanerandevusistemi.json.useCase.city.GetAllCityUseCase
import com.example.hastanerandevusistemi.json.useCase.district.GetAllDistrictUseCase
import com.example.hastanerandevusistemi.json.useCase.doktor.GetAllDoktorUseCase
import com.example.hastanerandevusistemi.json.useCase.gun.GetAllGunUseCase
import com.example.hastanerandevusistemi.json.useCase.hastane.GetHastaneUseCase
import com.example.hastanerandevusistemi.json.useCase.poliklinik.GetPoliklinikUseCase
import com.example.hastanerandevusistemi.json.useCase.saat.ChangeSaatValueUseCase
import com.example.hastanerandevusistemi.json.useCase.saat.GetAllSaatUseCase
import com.example.hastanerandevusistemi.model.Randevu
import dagger.Provides
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MakeAnAppointmentFragmentViewModel @Inject constructor(application: Application,
    private var getAllCityUseCase: GetAllCityUseCase,
    private var getAllDistrictUseCase: GetAllDistrictUseCase,
    private var getHastaneUseCase: GetHastaneUseCase,
    private var getPoliklinikUseCase: GetPoliklinikUseCase,
    private var getDoktorUseCase: GetAllDoktorUseCase,
    private var getGunUseCase: GetAllGunUseCase,
    private var getSaatUseCase: GetAllSaatUseCase,
    private var changeSaatValueUseCase: ChangeSaatValueUseCase,
) : BaseViewModel(application) {

    var il : MutableLiveData<List<CityEntity>?> = MutableLiveData()
    var ilce : MutableLiveData<List<DistrictEntity>?> = MutableLiveData()
    var hastane : MutableLiveData<List<HastaneEntity>?> = MutableLiveData()
    var poliklinik : MutableLiveData<List<PoliklinikEntity>?> = MutableLiveData()
    var doktor : MutableLiveData<List<DoktorEntity>?> = MutableLiveData()
    var gun : MutableLiveData<List<GunEntity>?> = MutableLiveData()
    var saat : MutableLiveData<List<SaatEntity>?> = MutableLiveData()

    var userId: MutableLiveData<Int?> = MutableLiveData()

    var selectedCityId: MutableLiveData<Int> = MutableLiveData()
    var selectedCityName: MutableLiveData<String> = MutableLiveData()
    var selectedDistrictId: MutableLiveData<Int> = MutableLiveData()
    var selectedDistrictName: MutableLiveData<String> = MutableLiveData()
    var selectedHospitalId: MutableLiveData<Int> = MutableLiveData()
    var selectedHospitalName: MutableLiveData<String> = MutableLiveData()
    var selectedDepertmantId: MutableLiveData<Int> = MutableLiveData()
    var selectedDepertmantName: MutableLiveData<String> = MutableLiveData()
    var selectedDoctorId: MutableLiveData<Int> = MutableLiveData()
    var selectedDoctorName: MutableLiveData<String> = MutableLiveData()
    var selectedDateId: MutableLiveData<Int> = MutableLiveData()
    var selectedDateName: MutableLiveData<String> = MutableLiveData()
    var selectedHourId: MutableLiveData<Int> = MutableLiveData()
    var selectedHourName: MutableLiveData<String> = MutableLiveData()

    fun getIller() {
        getAllCityUseCase.invoke().onEach {
            when (it) {
                is RequestState.Loading -> {
                    Log.d("TAG", "getData: Loading")
                }
                is RequestState.Success -> {
                    il.value = it.data
                }
                is RequestState.Error -> {
                    Log.d("TAG", "getData: Error")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getIlceler(ilId: Int) {
        getAllDistrictUseCase.invoke(ilId).onEach {
            when (it) {
                is RequestState.Loading -> {
                    Log.d("TAG", "getData: Loading")
                }
                is RequestState.Success -> {
                    ilce.value = it.data
                    Log.d("TAG", "getData: Success")
                }
                is RequestState.Error -> {
                    Log.d("TAG", "getData: Error")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getHastaneler(discId: Int) {
        getHastaneUseCase.invoke(discId).onEach {
            when (it) {
                is RequestState.Loading -> {
                    Log.d("TAG", "getData: Loading")
                }
                is RequestState.Success -> {
                    hastane.value = it.data
                    Log.d("TAG", "getData: Success")
                }
                is RequestState.Error -> {
                    Log.d("TAG", "getData: Error")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getPoliklinikler(hospitalId: Int) {
        getPoliklinikUseCase.invoke(hospitalId).onEach {
            when (it) {
                is RequestState.Loading -> {
                    Log.d("TAG", "getData: Loading")
                }
                is RequestState.Success -> {
                    poliklinik.value = it.data
                    Log.d("TAG", "getData: Success")
                }
                is RequestState.Error -> {
                    Log.d("TAG", "getData: Error")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getDoktorlar(poliklinikId: Int) {
        getDoktorUseCase.invoke(poliklinikId).onEach {
            when (it) {
                is RequestState.Loading -> {
                    Log.d("TAG", "getData: Loading")
                }
                is RequestState.Success -> {
                    doktor.value = it.data
                    Log.d("TAG", "getData: Success")
                }
                is RequestState.Error -> {
                    Log.d("TAG", "getData: Error")
                }
            }
        }.launchIn(viewModelScope)

    }

    fun getGunler(doctorId: Int) {
        getGunUseCase.invoke(doctorId).onEach {
            when (it) {
                is RequestState.Loading -> {
                    Log.d("TAG", "getData: Loading")
                }
                is RequestState.Success -> {
                    gun.value = it.data
                    Log.d("TAG", "getData: Success")
                }
                is RequestState.Error -> {
                    Log.d("TAG", "getData: Error")
                }
            }
        }.launchIn(viewModelScope)

    }

    fun getSaatler(dayId: Int) {
        getSaatUseCase.invoke(dayId).onEach {
            when (it) {
                is RequestState.Loading -> {
                    Log.d("TAG", "getData: Loading")
                }
                is RequestState.Success -> {
                    saat.value = it.data
                    Log.d("TAG", "getData: Success")
                }
                is RequestState.Error -> {
                    Log.d("TAG", "getData: Error")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun randevuAl(
        userId: Int,
        cityId: Int,
        cityName: String,
        hospitalId: Int,
        hospitalName: String,
        departmentId: Int,
        departmentName: String,
        doctorId: Int,
        doctorName: String,
        dateId: Int,
        dateName: String,
        hourId: Int,
        hourName: String
    ) {
        val randevu: ArrayList<Randevu> = arrayListOf()
        randevu.add(
            Randevu(
                userId,
                cityId,
                cityName,
                hospitalId,
                hospitalName,
                departmentId,
                departmentName,
                doctorId,
                doctorName,
                dateId,
                dateName,
                hourId,
                hourName
            )
        )

    }
}
