package com.example.hastanerandevusistemi.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.hastanerandevusistemi.AppDatabase
import com.example.hastanerandevusistemi.R
import com.example.hastanerandevusistemi.RequestState
import com.example.hastanerandevusistemi.databinding.FragmentProfileBinding
import com.example.hastanerandevusistemi.register.RegisterRepository
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private val viewModel: ProfileFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater)
        getUserInfo()
        return binding.root
    }

    fun getUserInfo() {
        val userTc = arguments?.getString("tc")
        val userPassword = arguments?.getString("password")
        if (userTc != null && userPassword != null) {
            viewModel.getUserInfo(userTc, userPassword)
            viewModel.userInfo.observe(viewLifecycleOwner) {
                when (it) {
                    is RequestState.Loading -> {
                    }

                    is RequestState.Success -> {
                        binding.name.text = it.data?.name
                        binding.surname.text = it.data?.surname
                        binding.tc.text = it.data?.TC.toString()
                        binding.gender.text = it.data?.gender
                        binding.birthday.text = it.data?.birthday
                        binding.mail.text = it.data?.email
                        binding.phone.text = it.data?.phone
                    }

                    is RequestState.Error -> {

                    }
                }
            }
        }
    }

}
