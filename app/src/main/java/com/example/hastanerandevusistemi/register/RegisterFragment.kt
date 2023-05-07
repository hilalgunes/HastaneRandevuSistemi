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
import com.example.hastanerandevusistemi.R
import com.example.hastanerandevusistemi.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private lateinit var registerViewModel: RegisterViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false)

        @BindingAdapter("selectedValue")
        fun setText(view: Spinner, value: String?) {
            if (value != null) {
                val adapter = view.adapter
                for (i in 0 until adapter.count) {
                    if (adapter.getItem(i).toString() == value) {
                        view.setSelection(i)
                        return
                    }
                }
            }
        }

        // Spinner adapter
        val genderArray = arrayOf("Erkek", "Kız")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, genderArray)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.genderSpinner.adapter = adapter

        binding.genderSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem = parent?.getItemAtPosition(position).toString()
                registerViewModel.Gender.value = selectedItem
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // do nothing
            }
        }




        registerViewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)
        binding.myViewModel = registerViewModel
        binding.lifecycleOwner = this

        // Observer functions for the ViewModel
        registerViewModel.navigateto.observe(viewLifecycleOwner, Observer { hasFinished->
            if (hasFinished == true){
                Log.i("MYTAG","insidi observe")
                displayUsersList()
                registerViewModel.doneNavigating()
            }
        })

        registerViewModel.userDetailsLiveData.observe(viewLifecycleOwner, Observer {
            Log.i("MYTAG",it.toString()+"000000000000000000000000")
        })

        registerViewModel.errotoast.observe(viewLifecycleOwner, Observer { hasError->
            if(hasError==true){
                Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
                registerViewModel.donetoast()
            }
        })

        registerViewModel.errotoastUsername.observe(viewLifecycleOwner, Observer { hasError->
            if(hasError==true){
                Toast.makeText(requireContext(), "UserName Already taken", Toast.LENGTH_SHORT).show()
                registerViewModel.donetoastUserName()
            }
        })

        return binding.root
    }


    private fun displayUsersList() {
        Log.i("MYTAG","insidisplayUsersList")

    }
}