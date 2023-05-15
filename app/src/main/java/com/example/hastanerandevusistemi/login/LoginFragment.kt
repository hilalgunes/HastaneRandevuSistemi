package com.example.hastanerandevusistemi.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        // ViewModelProvider aracılığıyla ViewModel'i oluştur
        viewModel = ViewModelProvider(this).get(LoginFragmentViewModel::class.java)

        // ViewModel'deki navigatetoUserDetails LiveData'sını gözlemle
        viewModel.navigateToHomeFragment.observe(viewLifecycleOwner, Observer { navigate ->
            if (navigate) {
                // Home Fragment'a geçiş işlemini gerçekleştir
                navigateToHomeFragment()
                // Geçiş işlemi tamamlandığında ViewModel'deki doneNavigatingUserDetails işlemini çağır
                viewModel.doneNavigatingUserDetails()
            }
        })

        // Register butonuna tıklama olayını ayarla
        binding.registerButton.setOnClickListener {
            // Register butonuna tıklandığında ViewModel'deki registerButton işlemini çağır
            viewModel.registerButton()
        }

        return binding.root
    }

    private fun navigateToHomeFragment() {
        // NavController'ı kullanarak Home Fragment'a geçiş yap
        findNavController().navigate(R.id.homePageFragment)
        viewModel.doneNavigatingUserDetails()
    }
}
