package com.example.hastanerandevusistemi.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.hastanerandevusistemi.AppDatabase
import com.example.hastanerandevusistemi.R
import com.example.hastanerandevusistemi.databinding.FragmentLoginBinding
import com.example.hastanerandevusistemi.register.RegisterRepository
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment(), View.OnClickListener{

    private lateinit var binding: FragmentLoginBinding
    private  val viewModel: LoginFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Veri bağlama işlemini yap
        binding = FragmentLoginBinding.inflate(layoutInflater)

        initView()
        return binding.root
    }

    private fun initView() {
        binding.registerButton.setOnClickListener(this)
        binding.loginButton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            binding.registerButton.id -> {
                findNavController().navigate(R.id.registerGecis)
            }
            binding.loginButton.id -> {
                login()
            }
        }
    }

    fun login() {
        if (!binding.TC.text.isNullOrEmpty()) {
            if (!binding.Sifre.text.isNullOrEmpty()) {
                viewModel.getUserByTcAndPassword(
                    binding.TC.text.toString(),
                    binding.Sifre.text.toString()
                )
                viewModel.saveUserInfo(
                    binding.TC.text.toString().toLong(),
                    binding.Sifre.text.toString()
                )
                viewModel.loginState.observe(viewLifecycleOwner) {
                    if (it) {
                        Toast.makeText(context, "Giriş Yaptınız", Toast.LENGTH_SHORT).show()
                        val bundle = Bundle().apply {
                            putString("tc", binding.TC.text.toString())
                            putString("password", binding.Sifre.text.toString())
                        }
                        findNavController().navigate(R.id.homePageFragment, bundle)
                    } else {
                        Toast.makeText(context, "Giriş Başarısız Bilgilerinizi Kontrol Ediniz", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(context, "Şifre Alanını Boş Bırakmayınız", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(context, "TC Alanını Boş Bırakmayınız", Toast.LENGTH_SHORT).show()
        }
    }
}
