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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.hastanerandevusistemi.R
import com.example.hastanerandevusistemi.databinding.FragmentRegisterBinding
import com.example.hastanerandevusistemi.login.LoginFragmentViewModel

class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private lateinit var viewModel: RegisterViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false)
        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)

        binding.textView.setOnClickListener {
            // Register butonuna tıklandığında ViewModel'deki registerButton işlemini çağır
            viewModel.loginButton()
            navigateToLoginFragment()
        }

        return binding.root
    }

    private fun navigateToLoginFragment() {
        findNavController().navigate(R.id.loginFragment)
    }


}