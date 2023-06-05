package com.example.hastanerandevusistemi.homePage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.hastanerandevusistemi.AppDatabase
import com.example.hastanerandevusistemi.R
import com.example.hastanerandevusistemi.RequestState
import com.example.hastanerandevusistemi.databinding.FragmentHomePageBinding
import com.example.hastanerandevusistemi.register.RegisterEntity
import com.example.hastanerandevusistemi.register.RegisterRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomePageFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentHomePageBinding
    private val viewModel: HomePageFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomePageBinding.inflate(layoutInflater)
        initView()
        getUserInfo()
        return binding.root

    }

    private fun initView() {
        binding.randevual.setOnClickListener(this)
        binding.bilgiler.setOnClickListener(this)
        binding.aktifrandevu.setOnClickListener(this)
        binding.cikis.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        when (v?.id) {

            binding.randevual.id -> {
                val bundle = Bundle().apply {
                    putString("tc", arguments?.getString("tc")!!)
                    putString("password", arguments?.getString("password")!!)
                }
                findNavController().navigate(R.id.makeAnAppointmentFragment, bundle)
            }

            binding.bilgiler.id -> {
                val bundle = Bundle().apply {
                    putString("tc", arguments?.getString("tc")!!)
                    putString("password", arguments?.getString("password")!!)
                }
                findNavController().navigate(R.id.profileFragment, bundle)
            }

            binding.aktifrandevu.id -> {
                val bundle = Bundle().apply {
                    putString("tc", arguments?.getString("tc")!!)
                    putString("password", arguments?.getString("password")!!)
                }
                findNavController().navigate(R.id.appointmentsFragment, bundle)
            }

            binding.cikis.id -> {
                findNavController().navigate(R.id.loginDonus)
            }
        }
    }

    fun getUserInfo() {
        val userTc = arguments?.getString("tc")
        val userPassword = arguments?.getString("password")
        if (userTc != null && userPassword != null) {
            viewModel.getUserInfo(userTc, userPassword)
            viewModel.userInfo.observe(viewLifecycleOwner) { requestState ->
                when (requestState) {
                    is RequestState.Loading -> {
                        // İstek yüklenirken yapılacak işlemler
                    }
                    is RequestState.Success -> {
                        val userInfo = requestState.data
                        val greetingMessage = "Sağlıklı Günler Dileriz Sn.${userInfo?.name}${userInfo?.surname}"
                        binding.textView.text = greetingMessage

                    }
                    is RequestState.Error -> {
                        // Hata durumunda yapılacak işlemler
                    }
                }
            }
        }
    }

}