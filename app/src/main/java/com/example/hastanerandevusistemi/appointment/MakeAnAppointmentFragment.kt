package com.example.hastanerandevusistemi.appointment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import com.example.hastanerandevusistemi.databinding.FragmentMakeAnAppointmentBinding


class MakeAnAppointmentFragment : Fragment() {

    private lateinit var binding: FragmentMakeAnAppointmentBinding
    private lateinit var viewModel: MakeAnAppointmentFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMakeAnAppointmentBinding.inflate(inflater, container, false)

        val application = requireNotNull(this.activity).application

        val dao = RandevuDatabase.getInstance(application).randevuDao()

        val repository = RandevuRepository(dao)

        val factory = MakeAnAppointmentFragmentViewModelFactory(repository, application)

        viewModel = ViewModelProvider(requireActivity(), factory).get(MakeAnAppointmentFragmentViewModel::class.java)

        binding.myViewModel = viewModel

        binding.lifecycleOwner = this

        viewModel.loadJsonData(requireContext())


        binding.ilSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedIl = parent?.getItemAtPosition(position) as String
                viewModel.il.value = selectedIl

                // İl seçildiğinde ilçeleri güncelle

                val ilceler = viewModel.getIlceler(selectedIl)
                binding.ilceSpinner.adapter = ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_spinner_item,
                    ilceler
                ).also { adapter ->
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                }

                // İl seçimi değiştiğinde ilçe, hastane, poliklinik, doktor, gün ve saat seçimlerini sıfırla
                viewModel.ilce.value = null
                viewModel.hastane.value = null
                viewModel.poliklinik.value = null
                viewModel.doktor.value = null
                viewModel.gun.value = null
                viewModel.saat.value = null
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                viewModel.il.value = null
            }
        }

        binding.ilceSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedIlce = parent?.getItemAtPosition(position) as String
                viewModel.ilce.value = selectedIlce

                // İlçe seçildiğinde hastaneleri güncelle
                val hastaneler = viewModel.getHastaneler(selectedIlce)
                binding.hastaneSpinner.adapter = ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_spinner_item,
                    hastaneler
                ).also { adapter ->
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                }

                // İlçe seçimi değiştiğinde hastane, poliklinik, doktor, gün ve saat seçimlerini sıfırla
                viewModel.hastane.value = null
                viewModel.poliklinik.value = null
                viewModel.doktor.value = null
                viewModel.gun.value = null
                viewModel.saat.value = null
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                viewModel.ilce.value = null
            }
        }

        binding.hastaneSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val selectedHastane = parent?.getItemAtPosition(position) as String
                    viewModel.hastane.value = selectedHastane
                    val selectedIlce = binding.ilceSpinner.selectedItem as String


                    // Hastane seçildiğinde poliklinikleri güncelle

                    val poliklinikler = viewModel.getPoliklinikler(selectedIlce, selectedHastane)
                    binding.poliklinikSpinner.adapter = ArrayAdapter(
                        requireContext(),
                        android.R.layout.simple_spinner_item,
                        poliklinikler
                    ).also { adapter ->
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    }

                    // Hastane seçimi değiştiğinde poliklinik, doktor, gün ve saat seçimlerini sıfırla
                    viewModel.poliklinik.value = null
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    viewModel.ilce.value = null
                    viewModel.doktor.value = null
                    viewModel.gun.value = null
                    viewModel.saat.value = null
                }
            }

        binding.poliklinikSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val selectedPoliklinik = parent?.getItemAtPosition(position) as String
                    val selectedHastane = binding.hastaneSpinner.selectedItem as String
                    val selectedIlce = binding.ilceSpinner.selectedItem as String
                    viewModel.poliklinik.value = selectedPoliklinik

                    // Poliklinik seçildiğinde doktorları güncelle
                    val doktorlar =
                        viewModel.getDoktorlar(selectedIlce, selectedHastane, selectedPoliklinik)
                    binding.doktorSpinner.adapter = ArrayAdapter(
                        requireContext(),
                        android.R.layout.simple_spinner_item,
                        doktorlar
                    ).also { adapter ->
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    }

                    // Poliklinik seçimi değiştiğinde doktor, gün ve saat seçimlerini sıfırla
                    viewModel.doktor.value = null
                    viewModel.gun.value = null
                    viewModel.saat.value = null
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    viewModel.poliklinik.value = null
                }
            }

        binding.doktorSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedDoktor = parent?.getItemAtPosition(position) as String
                val selectedHastane = binding.hastaneSpinner.selectedItem as String
                val selectedIlce = binding.ilceSpinner.selectedItem as String
                val selectedPoliklinik = binding.poliklinikSpinner.selectedItem as String
                viewModel.doktor.value = selectedDoktor

                // Doktor seçimi değiştiğinde günleri güncelle
                val gunler = viewModel.getGunler(
                    selectedIlce,
                    selectedHastane,
                    selectedPoliklinik,
                    selectedDoktor
                )
                binding.gunSpinner.adapter = ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_spinner_item,
                    gunler
                ).also { adapter ->
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                }

                // Doktor seçimi değiştiğinde gün ve saat seçimlerini sıfırla
                viewModel.gun.value = null
                viewModel.saat.value = null
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                viewModel.doktor.value = null
            }
        }

        binding.gunSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedGun = parent?.getItemAtPosition(position) as String
                val selectedDoktor = binding.doktorSpinner.selectedItem as String
                val selectedHastane = binding.hastaneSpinner.selectedItem as String
                val selectedIlce = binding.ilceSpinner.selectedItem as String
                val selectedPoliklinik = binding.poliklinikSpinner.selectedItem as String
                viewModel.gun.value = selectedGun

                // Gün seçimi değiştiğinde saatleri güncelle
                val saatler = viewModel.getSaatler(
                    selectedIlce,
                    selectedHastane,
                    selectedPoliklinik,
                    selectedDoktor,
                    selectedGun
                )
                binding.saatSpinner.adapter = ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_spinner_item,
                    saatler
                ).also { adapter ->
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                }

                // Gün seçimi değiştiğinde saat seçimini sıfırla
                viewModel.saat.value = null
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                viewModel.gun.value = null
            }
        }

        // Saat Spinner'ı seçimi için onItemSelectedListener
        binding.saatSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedSaat = parent?.getItemAtPosition(position) as String
                viewModel.saat.value = selectedSaat
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                viewModel.saat.value = null
            }
        }

        return binding.root

    }
    }