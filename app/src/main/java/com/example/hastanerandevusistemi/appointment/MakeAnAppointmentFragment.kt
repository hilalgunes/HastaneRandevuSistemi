package com.example.hastanerandevusistemi.appointment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.hastanerandevusistemi.R
import com.example.hastanerandevusistemi.databinding.FragmentMakeAnAppointmentBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MakeAnAppointmentFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentMakeAnAppointmentBinding
    private val viewModel: MakeAnAppointmentFragmentViewModel by viewModels()
    private lateinit var citiesAdapter: CustomSpinnerAdapter
    private lateinit var districtsAdapter: CustomSpinnerAdapter
    private lateinit var hospitalsAdapter: CustomSpinnerAdapter
    private lateinit var departmentsAdapter: CustomSpinnerAdapter
    private lateinit var doctorsAdapter: CustomSpinnerAdapter
    private lateinit var daysAdapter: CustomSpinnerAdapter
    private lateinit var hoursAdapter: CustomSpinnerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMakeAnAppointmentBinding.inflate(layoutInflater)
        initView()
        getCitiesData()
        return binding.root
    }

    private fun initView() {
        viewModel.getUserInfo(
            arguments?.getString("tc")!!,
            arguments?.getString("password")!!
        )
        binding.randevugor.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            binding.randevugor.id -> {
                observeSelectedAppointment()
                Toast.makeText(context, "Randevunuz Başarılı Bir Şekilde Oluşturulmuştur", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun observeSelectedAppointment() {
        viewModel.selectedCityId.observe(viewLifecycleOwner) { cityId ->
            if (cityId != null) {
                Log.d("selectedCityId", cityId.toString())
                viewModel.selectedHospitalId.observe(viewLifecycleOwner) { hospitalId ->
                    if (hospitalId != null) {
                        Log.d("selectedHospitalId", hospitalId.toString())
                        viewModel.selectedDepertmantId.observe(viewLifecycleOwner) { departmentId ->
                            if (departmentId != null) {
                                Log.d("selectedDepartmentId", departmentId.toString())
                                viewModel.selectedDoctorId.observe(viewLifecycleOwner) { doctorId ->
                                    if (doctorId != null) {
                                        Log.d("selectedDoctorId", doctorId.toString())
                                        viewModel.selectedDateId.observe(
                                            viewLifecycleOwner
                                        ) { dateId ->
                                            if (dateId != null) {
                                                Log.d("selectedDayId", dateId.toString())
                                                viewModel.selectedHourId.observe(
                                                    viewLifecycleOwner
                                                ) { hourId ->
                                                    if (hourId != null) {
                                                        Log.d("selectedHourId", hourId.toString())

                                                        viewModel.randevuAl(
                                                            viewModel.userId.value!!,
                                                            cityId,
                                                            viewModel.selectedCityName.value!!,
                                                            hospitalId,
                                                            viewModel.selectedHospitalName.value!!,
                                                            departmentId,
                                                            viewModel.selectedDepertmantName.value!!,
                                                            doctorId,
                                                            viewModel.selectedDoctorName.value!!,
                                                            dateId,
                                                            viewModel.selectedDateName.value!!,
                                                            hourId,
                                                            viewModel.selectedHourName.value!!
                                                        )

                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    fun getCitiesData() {
        viewModel.getIller()
        viewModel.il.observe(viewLifecycleOwner) {
            it?.let {
                citiesAdapter = CustomSpinnerAdapter(requireContext(), it)
                citiesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            }
            binding.ilSpinner.adapter = citiesAdapter
            binding.ilSpinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    it?.get(position)?.id?.let { it1 -> setDistricts(it1) }
                    viewModel.selectedCityId.value = it?.get(position)?.id
                    viewModel.selectedCityName.value = it?.get(position)?.name

                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
        }
    }

    private fun setDistricts(ilId: Int) {
        viewModel.getIlceler(ilId)
        viewModel.ilce.observe(viewLifecycleOwner) { districtEntityList ->
            districtEntityList.let { district ->
                district?.forEach {
                    if (it.ilId == ilId) {
                        districtsAdapter = CustomSpinnerAdapter(requireContext(), district)
                        districtsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    }
                }
                districtsAdapter =
                    district?.let { it1 ->
                        CustomSpinnerAdapter(
                            requireContext(),
                            it1
                        )
                    }!!
                districtsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            }
            binding.ilceSpinner.adapter = districtsAdapter
            binding.ilceSpinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    districtEntityList?.get(position)?.value?.let { it1 -> setHospital(it1) }
                    viewModel.selectedDistrictId.value =
                        districtEntityList?.get(position)?.value
                    viewModel.selectedDistrictName.value = districtEntityList?.get(position)?.text
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }

            }
        }
    }

    private fun setHospital(discId: Int) {
        viewModel.getHastaneler(discId)
        viewModel.hastane.observe(viewLifecycleOwner) { hospitalEntityList ->
            hospitalEntityList.let { hospital ->
                hospital?.forEach {
                    if (it.ilceId == discId) {
                        hospitalsAdapter = CustomSpinnerAdapter(requireContext(), hospital)
                        hospitalsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    }
                }
                hospitalsAdapter =
                    hospital?.let { it1 ->
                        CustomSpinnerAdapter(
                            requireContext(),
                            it1
                        )
                    }!!
                hospitalsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            }
            binding.hastaneSpinner.adapter = hospitalsAdapter
            binding.hastaneSpinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    hospitalEntityList?.get(position)?.value?.let { it1 -> setDepartment(it1) }
                    viewModel.selectedHospitalId.value =
                        hospitalEntityList?.get(position)?.value
                    viewModel.selectedHospitalName.value = hospitalEntityList?.get(position)?.text
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

            }
        }
    }

    private fun setDepartment(hospitalId: Int) {
        viewModel.getPoliklinikler(hospitalId)
        viewModel.poliklinik.observe(viewLifecycleOwner) { departmentEntityList ->
            departmentEntityList.let { department ->
                department?.forEach {
                    if (it.hastaneId == hospitalId) {
                        departmentsAdapter = CustomSpinnerAdapter(requireContext(), department)
                        departmentsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    }
                }
                departmentsAdapter =
                    department?.let { it1 ->
                        CustomSpinnerAdapter(
                            requireContext(),
                            it1
                        )
                    }!!
                departmentsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            }
            binding.poliklinikSpinner.adapter = departmentsAdapter
            binding.poliklinikSpinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    departmentEntityList?.get(position)?.value?.let { it1 -> setDoctor(it1) }
                    viewModel.selectedDepertmantId.value =
                        departmentEntityList?.get(position)?.value
                    viewModel.selectedDepertmantName.value =
                        departmentEntityList?.get(position)?.text
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

            }
        }
    }

    private fun setDoctor(departmentId: Int) {
        viewModel.getDoktorlar(departmentId)
        viewModel.doktor.observe(viewLifecycleOwner) { doctorEntityList ->
            doctorEntityList.let { doctor ->
                doctor?.forEach {
                    if (it.poliklinikId == departmentId) {
                        doctorsAdapter = CustomSpinnerAdapter(requireContext(), doctor)
                        doctorsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    }
                }
                doctorsAdapter =
                    doctor?.let { it1 ->
                        CustomSpinnerAdapter(
                            requireContext(),
                            it1
                        )
                    }!!
                doctorsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            }
            binding.doktorSpinner.adapter = doctorsAdapter
            binding.doktorSpinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    doctorEntityList?.get(position)?.value?.let { it1 -> setDay(it1) }
                    viewModel.selectedDoctorId.value =
                        doctorEntityList?.get(position)?.value
                    viewModel.selectedDoctorName.value = doctorEntityList?.get(position)?.text
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

            }
        }
    }

    private fun setDay(doctorId: Int) {
        viewModel.getGunler(doctorId)
        viewModel.gun.observe(viewLifecycleOwner) { dayEntityList ->
            dayEntityList.let { day ->
                day?.forEach {
                    if (it.doktorId == doctorId) {
                        daysAdapter = CustomSpinnerAdapter(requireContext(), day)
                        daysAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    }
                }
                daysAdapter =
                    day?.let { it1 ->
                        CustomSpinnerAdapter(
                            requireContext(),
                            it1
                        )
                    }!!
                daysAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            }
            binding.gunSpinner.adapter = daysAdapter
            binding.gunSpinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    dayEntityList?.get(position)?.value?.let { it1 -> setHour(it1) }
                    viewModel.selectedDateId.value =
                        dayEntityList?.get(position)?.value
                    viewModel.selectedDateName.value = dayEntityList?.get(position)?.text
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

            }
        }
    }

    fun setHour(dayId: Int) {
        viewModel.getSaatler(dayId)
        viewModel.saat.observe(viewLifecycleOwner) { hourEntityList ->
            hourEntityList.let { hour ->
                hour?.forEach {
                    if (it.gunId == dayId) {
                        hoursAdapter = CustomSpinnerAdapter(requireContext(), hour)
                        hoursAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    }
                }
                hoursAdapter =
                    hour?.let { it1 ->
                        CustomSpinnerAdapter(
                            requireContext(),
                            it1
                        )
                    }!!
                hoursAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            }
            binding.saatSpinner.adapter = hoursAdapter
            binding.saatSpinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    hourEntityList?.get(position)?.value?.let { it1 ->
                        Log.d("TAG", "onItemSelected:  $it1")
                        viewModel.selectedHourId.value = it1
                        viewModel.selectedHourName.value =
                            hourEntityList[position].text
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

            }
        }
    }

}