package com.example.hastanerandevusistemi.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.hastanerandevusistemi.R
import com.example.hastanerandevusistemi.databinding.ListItemBinding
import com.example.hastanerandevusistemi.register.RegisterEntity

class MyRecycleViewAdapter(private val usersList :List<RegisterEntity>): RecyclerView.Adapter<MyviewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyviewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ListItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.list_item,parent,false)
        return MyviewHolder(binding)
    }

    override fun getItemCount(): Int {
        return usersList.size
    }

    override fun onBindViewHolder(holder: MyviewHolder, position: Int) {
        holder.bind(usersList[position])

    }
}
class MyviewHolder(private val binding :ListItemBinding ):RecyclerView.ViewHolder(binding.root) {

    fun bind(user : RegisterEntity){
        binding.name.text = user.name
        binding.surname.text = user.surname
        binding.tc.text = user.TC
        binding.gender.text = user.gender
        binding.birthday.text = user.birthday
        binding.mail.text = user.email
        binding.phone.text = user.phone
    }
}
