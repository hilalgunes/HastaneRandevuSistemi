package com.example.hastanerandevusistemi.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.hastanerandevusistemi.AppDatabase
import com.example.hastanerandevusistemi.R
import com.example.hastanerandevusistemi.databinding.FragmentLoginBinding
import com.example.hastanerandevusistemi.register.RegisterRepository


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Veri bağlama işlemini yap
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        val application = requireNotNull(this.activity).application

        val dao = AppDatabase.getInstance(application).registerDao()

        val repository = RegisterRepository(dao)

        val factory = LoginViewModelFactory(repository, application)

        viewModel = ViewModelProvider(requireActivity(), factory).get(LoginFragmentViewModel::class.java)

        binding.myViewModel = viewModel

        binding.lifecycleOwner = this

        viewModel.navigateToLogin.observe(viewLifecycleOwner, Observer { hasFinished ->
            if (hasFinished == true) {
                Log.i("MYTAG", "inside observe")
                viewModel.doneNavigating()
                navigateToHomePageFragment()
            }
        })

        viewModel.navigatetoRegister.observe(viewLifecycleOwner, Observer { hasFinished ->
            if (hasFinished == true) {
                Log.i("MYTAG", "inside observe")
                viewModel.doneNavigating()
                navigateToRegisterFragment()
            }
        })

        viewModel.errotoast.observe(viewLifecycleOwner, Observer { hasError->
            if(hasError==true){
                Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
                viewModel.donetoast()
            }
        })

        viewModel.errotoastUser.observe(viewLifecycleOwner, Observer { hasError->
            if(hasError==true){
                Toast.makeText(requireContext(), "User doesnt exist,please Register!", Toast.LENGTH_SHORT).show()
                viewModel.donetoastErrorUsername()
            }
        })

        viewModel.errorToastInvalidPassword.observe(viewLifecycleOwner, Observer { hasError->
            if(hasError==true){
                Toast.makeText(requireContext(), "Please check your Password", Toast.LENGTH_SHORT).show()
                viewModel.donetoastInvalidPassword()
            }
        })

        return binding.root
    }

    private fun navigateToHomePageFragment() {
        findNavController().navigate(R.id.homePageFragmentgecis)
    }

    private fun navigateToRegisterFragment() {
        findNavController().navigate(R.id.registerGecis)
    }
}
