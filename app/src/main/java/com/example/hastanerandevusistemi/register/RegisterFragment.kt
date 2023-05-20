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
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.hastanerandevusistemi.AppDatabase
import com.example.hastanerandevusistemi.R
import com.example.hastanerandevusistemi.databinding.FragmentRegisterBinding


class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private lateinit var viewModel: RegisterViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false)

        val application = requireNotNull(this.activity).application

        val dao = AppDatabase.getInstance(application).registerDao()

        val repository = RegisterRepository(dao)

        val factory = RegisterViewModelFactory(repository, application)

        viewModel = ViewModelProvider(requireActivity(), factory).get(RegisterViewModel::class.java)

        // Spinner için veri kaynağını belirleme
        val genderOptions = arrayOf("Kız", "Erkek")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, genderOptions)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.gender.adapter = adapter

        // Spinner seçim olaylarını dinleme
        binding.myViewModel = viewModel
        binding.gender.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                viewModel.gender.value = parent?.getItemAtPosition(position) as String?
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // İstenirse, bir şey yapabilirsiniz, örneğin varsayılan değeri atayabilirsiniz.
            }
        }

        binding.myViewModel = viewModel

        binding.lifecycleOwner = this

        viewModel.navigateToLoginFragment.observe(viewLifecycleOwner, Observer { hasFinished ->
            if (hasFinished == true) {
                Log.i("MYTAG", "inside observe")
                viewModel.doneNavigating()
                navigateToLoginFragment()

            }
        })

        viewModel.errotoast.observe(viewLifecycleOwner, Observer { hasError->
            if(hasError==true){
                Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
                viewModel.donetoast()
            }
        })

        return binding.root
    }

     private fun navigateToLoginFragment() {
      findNavController().navigate(R.id.mainActivity)
    }
}