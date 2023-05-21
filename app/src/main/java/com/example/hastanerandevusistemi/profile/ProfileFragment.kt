package com.example.hastanerandevusistemi.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.hastanerandevusistemi.AppDatabase
import com.example.hastanerandevusistemi.R
import com.example.hastanerandevusistemi.databinding.FragmentProfileBinding
import com.example.hastanerandevusistemi.register.RegisterDao
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


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeUserProfile()
    }


    private fun observeUserProfile() {
        viewModel.users.observe(viewLifecycleOwner) { userList ->
            if (userList.isNotEmpty()) {
                val user = userList[0] // İlk kullanıcıyı al
                binding.name.text = user.name
                binding.surname.text = user.surname
                binding.tc.text = user.TC
                binding.gender.text = user.gender
                binding.birthday.text = user.birthday
                binding.mail.text = user.email
                binding.phone.text = user.phone
            }
        }
    }

}
