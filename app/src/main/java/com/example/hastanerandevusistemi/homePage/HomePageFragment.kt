package com.example.hastanerandevusistemi.homePage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.hastanerandevusistemi.AppDatabase
import com.example.hastanerandevusistemi.R
import com.example.hastanerandevusistemi.databinding.FragmentHomePageBinding
import com.example.hastanerandevusistemi.register.RegisterEntity
import com.example.hastanerandevusistemi.register.RegisterRepository
import kotlinx.coroutines.launch

class HomePageFragment : Fragment() {

    private lateinit var binding: FragmentHomePageBinding
    private lateinit var viewModel: HomePageFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_page, container, false)

        val application = requireNotNull(this.activity).application

        val dao = AppDatabase.getInstance(application).registerDao()

        val repository = RegisterRepository(dao)

        val factory = HomePageFactory(repository, application)

        viewModel = ViewModelProvider(requireActivity(), factory).get(HomePageFragmentViewModel::class.java)

        binding.myViewModel = viewModel

        binding.lifecycleOwner = this

        val tcNo = "kullanıcınınTcNo"
        val password = "kullanıcınınParolası"

        lifecycleScope.launch {
            val kullanici: RegisterEntity? = dao.getUser(tcNo, password)
            kullanici?.let { user ->
                val kullaniciAdi = user.name
                val metin = "Sn. $kullaniciAdi, sağlıklı günleri hedefleyin!"
                binding.textView.text = metin
            }
        }

        viewModel.navigateto.observe(viewLifecycleOwner, Observer { hasFinished ->
            if (hasFinished == true) {
                viewModel.doneNavigating()
                navigateToProfileFragment()
            }
        })

        binding.cikis.setOnClickListener {
            navigateToLoginFragment()
        }
        binding.randevual.setOnClickListener {
            navigateToMakeAnAppointmentFragment()
        }
        return binding.root
    }

    private fun navigateToProfileFragment(){
        findNavController().navigate(R.id.profilGecis)
    }

    private fun navigateToMakeAnAppointmentFragment() {
        findNavController().navigate(R.id.makeAnAppointmentFragment)
    }

    private fun navigateToLoginFragment() {
        findNavController().navigate(R.id.loginFragment)
    }


}