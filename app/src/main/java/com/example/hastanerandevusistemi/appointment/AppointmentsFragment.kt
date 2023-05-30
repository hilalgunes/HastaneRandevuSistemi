package com.example.hastanerandevusistemi.appointment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hastanerandevusistemi.RequestState
import com.example.hastanerandevusistemi.databinding.FragmentAppointmentsBinding
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
        initView()
        return binding.root
    }
    private fun initView() {
        val userTc = arguments?.getString("tc")
        val userPassword = arguments?.getString("password")
        if (userTc != null) {
            if (userPassword != null) {
               viewModel.getUserId(userTc, userPassword)
            }
        }
        getAppointments()
    }
    private fun getAppointments() {
        val appointmentList = ArrayList<RandevuEntity>()
        viewModel.userId.observe(viewLifecycleOwner) {
            when (it) {
                is RequestState.Success -> {
                    it.data?.userId?.let { it1 ->
                        viewModel.getAppointmentByUser(
                            it1
                        )
                    }
                }
                is RequestState.Error -> {
                    Log.d("TAG", "getMyAppointment: error")
                }
                is RequestState.Loading -> {
                    Log.d("TAG", "getMyAppointment: Loading")
                }
            }
        }
        viewModel.myAppointment?.observe(viewLifecycleOwner) {
            when (it) {
                is RequestState.Success -> {
                    it.data?.let { it1 -> appointmentList.addAll(it1) }
                    adapter.submitList(appointmentList)
                }
                is RequestState.Loading -> {
                    Log.d("TAG", "getMyAppointment: Loading")
                }
                is RequestState.Error -> {
                    Log.d("TAG", "getMyAppointment: Error")
                }
                else -> {}
            }
        }
    }
    private fun initRecyclerView() {
        adapter = AppointmentsFragmentRecyclerAdapter()
        binding.recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = adapter
    }
}