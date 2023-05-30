package com.example.hastanerandevusistemi.register

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.hastanerandevusistemi.AppDatabase
import com.example.hastanerandevusistemi.R
import com.example.hastanerandevusistemi.databinding.FragmentRegisterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentRegisterBinding
    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(layoutInflater)


        // Spinner için veri kaynağını belirleme
        val genderOptions = arrayOf("Kız", "Erkek")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, genderOptions)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.gender.adapter = adapter

        // Spinner seçim olaylarını dinleme
        binding.gender.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                viewModel.selectedGender.value = adapter.getItem(position) as String?
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        initView()
        return binding.root
    }

    private fun initView() {
        binding.textView.setOnClickListener(this)
        binding.registerbutton.setOnClickListener(this)
    }
    override fun onClick(v: View?) {
        when (v?.id) {
            binding.textView.id -> {
                findNavController().navigate(R.id.loginFragment)
            }

            binding.registerbutton.id -> {
                register()
            }
        }

    }

    private fun register() {
        if (!binding.ad.text.isNullOrEmpty()) {
            if (!binding.soyad.text.isNullOrEmpty()) {
                if (!binding.tcNo.text.isNullOrEmpty()) {
                    if (!binding.birthdayy.text.isNullOrEmpty()) {
                        if (!binding.email.text.isNullOrEmpty()) {
                            if (!binding.telefon.text.isNullOrEmpty()) {
                                if (!binding.sifre.text.isNullOrEmpty()) {
                                    val selectedGender = binding.gender.selectedItem.toString()
                                    viewModel.addUser(
                                        binding.ad.text.toString(),
                                        binding.soyad.text.toString(),
                                        binding.tcNo.text.toString(),
                                        selectedGender,
                                        binding.birthdayy.text.toString(),
                                        binding.email.text.toString(),
                                        binding.telefon.text.toString(),
                                        binding.sifre.text.toString())
                                }
                            }
                        }
                    }
                }
            }
        }
        viewModel.registerState.observe(this) {
            when (it) {
                true -> {
                    Toast.makeText(context, "Kayıt Başarılı", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.loginGecis)
                }
                false -> {
                    Toast.makeText(context, "Kayıt Başarısız Tüm Alanları Doldurduğunuzdan Emin Olun", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}