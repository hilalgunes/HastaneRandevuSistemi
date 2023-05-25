package com.example.hastanerandevusistemi.appointment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hastanerandevusistemi.R
import com.example.hastanerandevusistemi.databinding.FragmentAppointmentsBinding
import com.example.hastanerandevusistemi.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AppointmentsFragment : Fragment() {

    private lateinit var binding: FragmentAppointmentsBinding
    private  val viewModel: AppointmentsFragmentViewModel by viewModels()
    private lateinit var adapter: AppointmentsFragmentRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =  FragmentAppointmentsBinding.inflate(layoutInflater)
        initRecyclerView()
        return binding.root
    }

    private fun initRecyclerView() {
        adapter = AppointmentsFragmentRecyclerAdapter()
        binding.recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = adapter
    }
}