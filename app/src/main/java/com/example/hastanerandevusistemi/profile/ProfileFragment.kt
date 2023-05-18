package com.example.hastanerandevusistemi.profile

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hastanerandevusistemi.AppDatabase
import com.example.hastanerandevusistemi.R
import com.example.hastanerandevusistemi.databinding.FragmentProfileBinding
import com.example.hastanerandevusistemi.login.LoginFragmentViewModel
import com.example.hastanerandevusistemi.register.RegisterRepository

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var viewModel: ProfileFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)

        val application = requireNotNull(this.activity).application

        val dao = AppDatabase.getInstance(application).registerDao()

        val repository = RegisterRepository(dao)

        val factory = ProfileFactory(repository, application)

        viewModel = ViewModelProvider(requireActivity(), factory).get(ProfileFragmentViewModel::class.java)

        binding.myViewModel = viewModel

        binding.lifecycleOwner = this


        initRecyclerView()

        return binding.root
    }

    private fun initRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this.context)
        displayUsersList()
    }

    private fun displayUsersList() {
        Log.i("MYTAG", "Inside ...UserDetails..Fragment")
        viewModel.users.observe(viewLifecycleOwner, Observer {
            binding.recyclerView.adapter = MyRecycleViewAdapter(it)
        })

    }
}