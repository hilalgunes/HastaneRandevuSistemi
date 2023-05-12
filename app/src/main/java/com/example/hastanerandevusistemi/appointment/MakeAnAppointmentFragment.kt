package com.example.hastanerandevusistemi.appointment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.hastanerandevusistemi.R
import com.example.hastanerandevusistemi.databinding.FragmentMakeAnAppointmentBinding

class MakeAnAppointmentFragment : Fragment() {

    private lateinit var binding: FragmentMakeAnAppointmentBinding
    private lateinit var viewModel: AppointmentsFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_make_an_appointment, container, false)
        return binding.root
    }
}