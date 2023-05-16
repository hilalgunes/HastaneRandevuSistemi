package com.example.hastanerandevusistemi.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.hastanerandevusistemi.R
import com.example.hastanerandevusistemi.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Veri bağlama işlemini yap
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        viewModel = ViewModelProvider(this).get(LoginFragmentViewModel::class.java)


        // Register butonuna tıklama olayını ayarla
        binding.registerButton.setOnClickListener {
            // Register butonuna tıklandığında ViewModel'deki registerButton işlemini çağır
            viewModel.registerButton()
            navigateToRegisterFragment()
        }

        // Login butonuna tıklama olayını ayarla
        binding.loginButton.setOnClickListener {
            // Login butonuna tıklandığında ViewModel'deki loginButton işlemini çağır
            viewModel.loginButton()
            navigateToHomePageFragment()
        }

        return binding.root
    }

    private fun navigateToHomePageFragment() {
        findNavController().navigate(R.id.homePageFragmentgecis)
    }

    private fun navigateToRegisterFragment() {
        findNavController().navigate(R.id.registerGecis)
    }
}
