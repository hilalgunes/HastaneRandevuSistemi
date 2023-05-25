package com.example.hastanerandevusistemi

import com.example.hastanerandevusistemi.model.*
import com.google.gson.Gson
import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hastanerandevusistemi.json.useCase.city.SaveCityUseCase
import com.example.hastanerandevusistemi.json.useCase.district.SaveDistrictUseCase
import com.example.hastanerandevusistemi.json.useCase.doktor.SaveDoktorUseCase
import com.example.hastanerandevusistemi.json.useCase.gun.SaveGunUseCase
import com.example.hastanerandevusistemi.json.useCase.hastane.SaveHastaneUseCase
import com.example.hastanerandevusistemi.json.useCase.poliklinik.SavePoliklinikUseCase
import com.example.hastanerandevusistemi.json.useCase.saat.SaveSaatUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SplashActivityViewModel @Inject constructor(
    @ApplicationContext var context: Context) : ViewModel() {

    @Inject
    lateinit var saveCityUseCase: SaveCityUseCase
    @Inject
    lateinit var saveDistrictUseCase: SaveDistrictUseCase
    @Inject
    lateinit var saveHastaneUseCase: SaveHastaneUseCase
    @Inject
    lateinit var savePoliklinikUseCase: SavePoliklinikUseCase
    @Inject
    lateinit var saveDoktorUseCase: SaveDoktorUseCase
    @Inject
    lateinit var saveGunUseCase: SaveGunUseCase
    @Inject
    lateinit var saveSaatUseCase: SaveSaatUseCase

    private var data: Data? = null
    private var city: ArrayList<City> = arrayListOf()
    private var district: ArrayList<District> = arrayListOf()
    private var hastane: ArrayList<Hastane> = arrayListOf()
    private var poliklinik: ArrayList<Polikinlik> = arrayListOf()
    private var doktor: ArrayList<Doktor> = arrayListOf()
    private var gun: ArrayList<Gunler> = arrayListOf()
    private var saat: ArrayList<Saatler> = arrayListOf()

    fun setCityData() {
        try {
            val gson = Gson()
            data = gson.fromJson(
                Helper().getJsonDataFromAssets(context, "hastanerandevu.json"),
                Data::class.java
            )
            data?.data?.forEach {
                city.add(
                    City(
                        it.districts,
                        it.text,
                        it.value
                    )
                )
            }
            Log.d("TAG", "setCityData: ${data?.data?.get(0)?.text}")
            saveCityUseCase.invoke(city).onEach {
                when (it) {
                    is RequestState.Loading -> {
                        Log.d("TAG", "setCityData: ")
                    }

                    is RequestState.Success -> {
                        Log.d("TAG", "setCityData: Success")
                    }

                    is RequestState.Error -> {
                        Log.d("TAG", "setCityData: Error")
                    }
                }
            }.launchIn(viewModelScope)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun setDistrictData() {
        try {
            val gson = Gson()
            data = gson.fromJson(
                Helper().getJsonDataFromAssets(context, "hastanerandevu.json"),
                Data::class.java
            )
            data?.data?.forEach {
                it.districts.forEach { district ->
                    this.district.add(
                        District(
                            district.hastane,
                            district.ilId,
                            district.text,
                            district.value
                        )
                    )
                }
            }
            Log.d("TAG", "setDistrictData: ${data?.data?.get(0)?.text}")
            saveDistrictUseCase.invoke(district).onEach {
                when (it) {
                    is RequestState.Loading -> {
                        Log.d("TAG", "setDistrictData: ")
                    }

                    is RequestState.Success -> {
                        Log.d("TAG", "setDistrictData: Success")
                    }

                    is RequestState.Error -> {
                        Log.d("TAG", "setDistrictData: Error")
                    }
                }
            }.launchIn(viewModelScope)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
    fun setHastaneData() {
        try {
            val gson = Gson()
            data = gson.fromJson(
                Helper().getJsonDataFromAssets(context, "hastanerandevu.json"),
                Data::class.java
            )
            data?.data?.forEach {
                it.districts.forEach { district ->
                    district.hastane.forEach { hastane ->
                        this.hastane.add(
                            Hastane(
                                hastane.ilceId,
                                hastane.polikinlik,
                                hastane.text,
                                hastane.value
                            )
                        )
                    }
                }
            }
            Log.d("TAG", "setHospitalData: ${data?.data?.get(0)?.text}")
            saveHastaneUseCase.invoke(hastane).onEach {
                when (it) {
                    is RequestState.Loading -> {
                        Log.d("TAG", "setHospitalData: ")
                    }

                    is RequestState.Success -> {
                        Log.d("TAG", "setHospitalData: Success")
                    }

                    is RequestState.Error -> {
                        Log.d("TAG", "setHospitalData: Error")
                    }
                }
            }.launchIn(viewModelScope)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun setPoliklinikData() {
        try {
            val gson = Gson()
            data = gson.fromJson(
                Helper().getJsonDataFromAssets(context, "hastanerandevu.json"),
                Data::class.java
            )
            data?.data?.forEach {
                it.districts.forEach { district ->
                    district.hastane.forEach { hastane ->
                        hastane.polikinlik.forEach { polikinlik ->
                            this.poliklinik.add(
                                Polikinlik(
                                    polikinlik.doktor,
                                    polikinlik.hastaneId,
                                    polikinlik.text,
                                    polikinlik.value
                                )
                            )
                        }
                    }
                }
            }
            Log.d("TAG", "setPolyclinicData: ${data?.data?.get(0)?.text}")
            savePoliklinikUseCase.invoke(poliklinik).onEach {
                when (it) {
                    is RequestState.Loading -> {
                        Log.d("TAG", "setPolyclinicData: ")
                    }

                    is RequestState.Success -> {
                        Log.d("TAG", "setPolyclinicData: Success")
                    }

                    is RequestState.Error -> {
                        Log.d("TAG", "setPolyclinicData: Error")
                    }
                }
            }.launchIn(viewModelScope)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun setDoktorData() {
        try {
            val gson = Gson()
            data = gson.fromJson(
                Helper().getJsonDataFromAssets(context, "hastanerandevu.json"),
                Data::class.java
            )
            data?.data?.forEach {
                it.districts.forEach { district ->
                    district.hastane.forEach { hastane ->
                        hastane.polikinlik.forEach { poliklinik ->
                            poliklinik.doktor.forEach { doktor ->
                                this.doktor.add(
                                    Doktor(
                                        doktor.gunler,
                                        doktor.poliklinikId,
                                        doktor.name,
                                        doktor.text,
                                        doktor.value
                                    )
                                )
                            }
                        }
                    }
                }
            }
            Log.d("TAG", "setDoctorData: ${data?.data?.get(0)?.text}")
            saveDoktorUseCase.invoke(doktor).onEach {
                when (it) {
                    is RequestState.Loading -> {
                        Log.d("TAG", "setDoctorData: ")
                    }

                    is RequestState.Success -> {
                        Log.d("TAG", "setDoctorData: Success")
                    }

                    is RequestState.Error -> {
                        Log.d("TAG", "setDoctorData: Error")
                    }
                }
            }.launchIn(viewModelScope)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun setGunData() {
        try {
            val gson = Gson()
            data = gson.fromJson(
                Helper().getJsonDataFromAssets(context, "hastanerandevu.json"),
                Data::class.java
            )
            data?.data?.forEach {
                it.districts.forEach { district ->
                    district.hastane.forEach { hastane ->
                        hastane.polikinlik.forEach { poliklinik ->
                            poliklinik.doktor.forEach { doktor ->
                                doktor.gunler.forEach { day ->
                                    this.gun.add(
                                        Gunler(
                                            day.doktorId,
                                            day.saatler,
                                            day.text,
                                            day.value
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
            }
            saveGunUseCase.invoke(gun).onEach {
                when (it) {
                    is RequestState.Loading -> {
                        Log.d("TAG", "setDaysData: ")
                    }

                    is RequestState.Success -> {
                        Log.d("TAG", "setDaysData: Success")
                    }

                    is RequestState.Error -> {
                        Log.d("TAG", "setDaysData: Error")
                    }
                }
            }.launchIn(viewModelScope)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun setSaatData() {
        try {
            val gson = Gson()
            data = gson.fromJson(
                Helper().getJsonDataFromAssets(context, "hastanerandevu.json"),
                Data::class.java
            )
            data?.data?.forEach {
                it.districts.forEach { district ->
                    district.hastane.forEach { hastane ->
                        hastane.polikinlik.forEach { poliklinik ->
                            poliklinik.doktor.forEach { doktor ->
                                doktor.gunler.forEach { gun ->
                                    gun.saatler.forEach { saat ->
                                        this.saat.add(
                                            Saatler(
                                                saat.selected,
                                                saat.gunId,
                                                saat.text,
                                                saat.value
                                            )
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
            Log.d("TAG", "setHourData: ${data?.data?.get(0)?.text}")
            saveSaatUseCase.invoke(saat).onEach {
                when (it) {
                    is RequestState.Loading -> {
                        Log.d("TAG", "setHourData: ")
                    }

                    is RequestState.Success -> {
                        Log.d("TAG", "setHourData: Success")
                    }

                    is RequestState.Error -> {
                        Log.d("TAG", "setHourData: Error")
                    }
                }
            }.launchIn(viewModelScope)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

