package com.example.hastanerandevusistemi.appointment
import android.app.Application
import android.content.Context
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hastanerandevusistemi.model.Data
import com.google.gson.Gson

class MakeAnAppointmentFragmentViewModel(private val repository: RandevuRepository, application: Application) :
    AndroidViewModel(application) , Observable {

    @Bindable
    val il = MutableLiveData<String?>()
    @Bindable
    val ilce = MutableLiveData<String?>()
    @Bindable
    val hastane = MutableLiveData<String?>()
    @Bindable
    val poliklinik = MutableLiveData<String?>()
    @Bindable
    val doktor = MutableLiveData<String?>()
    @Bindable
    val gun = MutableLiveData<String?>()
    @Bindable
    val saat = MutableLiveData<String?>()

    private val randevuList: MutableLiveData<List<RandevuEntity>> = MutableLiveData()
    private val ilcelerMap: MutableMap<String, List<String>> = mutableMapOf()
    private val hastanelerMap: MutableMap<String, List<String>> = mutableMapOf()
    private val polikliniklerMap: MutableMap<String, List<String>> = mutableMapOf()
    private val doktorlarMap: MutableMap<String, List<String>> = mutableMapOf()
    private val gunlerMap: MutableMap<String, List<String>> = mutableMapOf()
    private val saatlerMap: MutableMap<String, List<String>> = mutableMapOf()


    fun loadJsonData(context: Context) {
        val jsonFile: String = context.assets.open("hastanerandevu.json").bufferedReader().use {
            it.readText()
        }
        val gson = Gson()
        val data: Data = gson.fromJson(jsonFile, Data::class.java)
        val entityList = convertToEntityList(data)
        randevuList.value = entityList
    }

    fun getRandevuList(): LiveData<List<RandevuEntity>> {
        return randevuList
    }

    fun  makeAnAppointment() {

    }

    fun getIller(): List<String> {
        val iller = mutableListOf<String>()
        val randevuList = randevuList.value
        if (randevuList != null) {
            for (randevu in randevuList) {
                if (!iller.contains(randevu.il)) {
                    iller.add(randevu.il)
                }
            }
        }
        return iller
    }

    fun getIlceler(il: String): List<String> {
        val ilceler = ilcelerMap[il]
        if (ilceler != null) {
            return ilceler
        }
        val ilceList = mutableListOf<String>()
        val randevuList = randevuList.value

        if (randevuList != null) {
            for (randevu in randevuList) {
                if (randevu.il == il && !ilceList.contains(randevu.ilce)) {
                    ilceList.add(randevu.ilce)
                }
            }
        }
        // İlçeleri ilcelerMap'e kaydet
        ilcelerMap[il] = ilceList

        return ilceList
    }

    fun getHastaneler(ilce: String): List<String> {
        val hastaneler = hastanelerMap[ilce]
        if (hastaneler != null) {
            return hastaneler
        }
        val hastaneList = mutableListOf<String>()
        val randevuList = randevuList.value
        if (randevuList != null) {
            for (randevu in randevuList) {
                if (randevu.ilce == ilce && !hastaneList.contains(randevu.hastane)) {
                    hastaneList.add(randevu.hastane)
                }
            }
        }
        hastanelerMap[ilce] = hastaneList
        return hastaneList
    }

    fun getPoliklinikler(ilce: String, hastane: String): List<String> {
        val poliklinikler = polikliniklerMap["$ilce-$hastane"]
        if (poliklinikler != null) {
            return poliklinikler
        }
        val poliklinikList = mutableListOf<String>()
        val randevuList = randevuList.value
        if (randevuList != null) {
            for (randevu in randevuList) {
                if (randevu.ilce == ilce && randevu.hastane == hastane && !poliklinikList.contains(randevu.poliklinik)) {
                    poliklinikList.add(randevu.poliklinik)
                }
            }
        }
        polikliniklerMap["$ilce-$hastane"] = poliklinikList
        return poliklinikList
    }

    fun getDoktorlar(ilce: String, hastane: String, poliklinik: String): List<String> {
        val doktorlar = doktorlarMap["$ilce-$hastane-$poliklinik"]
        if (doktorlar != null) {
            return doktorlar
        }
        val doktorList = mutableListOf<String>()
        val randevuList = randevuList.value
        if (randevuList != null) {
            for (randevu in randevuList) {
                if (randevu.ilce == ilce && randevu.hastane == hastane && randevu.poliklinik == poliklinik &&
                    !doktorList.contains(randevu.doktor)) {
                    doktorList.add(randevu.doktor)
                }
            }
        }
        doktorlarMap["$ilce-$hastane-$poliklinik"] = doktorList
        return doktorList
    }

    fun getGunler(ilce: String, hastane: String, poliklinik: String, doktor: String): List<String> {
        val gunler = gunlerMap["$ilce-$hastane-$poliklinik-$doktor"]
        if (gunler != null) {
            return gunler
        }
        val gunList = mutableListOf<String>()
        val randevuList = randevuList.value
        if (randevuList != null) {
            for (randevu in randevuList) {
                if (randevu.ilce == ilce && randevu.hastane == hastane && randevu.poliklinik == poliklinik &&
                    randevu.doktor == doktor && !gunList.contains(randevu.gun)) {
                    gunList.add(randevu.gun)
                }
            }
        }
        gunlerMap["$ilce-$hastane-$poliklinik-$doktor"] = gunList
        return gunList
    }

    fun getSaatler(ilce: String, hastane: String, poliklinik: String, doktor: String, gun: String): List<String> {
        val saatler = saatlerMap["$ilce-$hastane-$poliklinik-$doktor-$gun"]
        if (saatler != null) {
            return saatler
        }
        val saatList = mutableListOf<String>()
        val randevuList = randevuList.value
        if (randevuList != null) {
            for (randevu in randevuList) {
                if (randevu.ilce == ilce && randevu.hastane == hastane && randevu.poliklinik == poliklinik &&
                    randevu.doktor == doktor && randevu.gun == gun && !saatList.contains(randevu.saat)) {
                    saatList.add(randevu.saat)
                }
            }
        }
        saatlerMap["$ilce-$hastane-$poliklinik-$doktor-$gun"] = saatList
        return saatList
    }

    private fun convertToEntityList(data: Data): List<RandevuEntity> {
        val randevuList = mutableListOf<RandevuEntity>()
        for (city in data.data.orEmpty()) {
            for (district in city.districts.orEmpty()) {
                for (hastane in district.hastane.orEmpty()) {
                    for (poliklinik in hastane.polikinlik.orEmpty()) {
                        for (doktor in poliklinik.doktor.orEmpty()) {
                            for (gun in doktor.gunler.orEmpty()) {
                                for (saat in gun.saatler.orEmpty()) {
                                    val randevu = RandevuEntity(
                                        il = city.text.orEmpty(),
                                        ilce = district.text.orEmpty(),
                                        hastane = hastane.text.orEmpty(),
                                        poliklinik = poliklinik.text.orEmpty(),
                                        doktor = doktor.name.orEmpty(),
                                        gun = gun.text.orEmpty(),
                                        saat = saat.text.orEmpty()
                                    )
                                    randevuList.add(randevu)
                                }
                            }
                        }
                    }
                }
            }
        }
        return randevuList
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }
}