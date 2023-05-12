package com.example.hastanerandevusistemi.appointment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.hastanerandevusistemi.R
import com.example.hastanerandevusistemi.databinding.FragmentAppointmentsBinding
import com.example.hastanerandevusistemi.databinding.FragmentLoginBinding

class AppointmentsFragment : Fragment() {

    private lateinit var binding: FragmentAppointmentsBinding
    private lateinit var viewModel: AppointmentsFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_appointments, container, false)
        return binding.root
    }
}