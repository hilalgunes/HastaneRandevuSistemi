package com.example.hastanerandevusistemi.homePage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.hastanerandevusistemi.R
import com.example.hastanerandevusistemi.databinding.FragmentHomePageBinding
import com.example.hastanerandevusistemi.login.LoginFragmentViewModel

class HomePageFragment : Fragment() {

    private lateinit var binding: FragmentHomePageBinding
    private lateinit var viewModel: HomePageFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_page, container, false)
        viewModel = ViewModelProvider(this).get(HomePageFragmentViewModel::class.java)

        binding.cikis.setOnClickListener {
            navigateToLoginFragment()
        }
        binding.randevual.setOnClickListener {
            navigateToMakeAnAppointmentFragment()
        }
        return binding.root
    }

    private fun navigateToMakeAnAppointmentFragment() {
        findNavController().navigate(R.id.makeAnAppointmentFragment)
    }

    private fun navigateToLoginFragment() {
        findNavController().navigate(R.id.loginFragment)
    }
}