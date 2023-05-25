package com.example.hastanerandevusistemi.appointment

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.hastanerandevusistemi.R
import com.example.hastanerandevusistemi.databinding.ListItemBinding

class AppointmentsFragmentRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var appointmentList: ArrayList<RandevuEntity> = arrayListOf()

        class ViewHolder(view : ListItemBinding) :
            RecyclerView.ViewHolder(view.root) {
            val binding: ListItemBinding = view
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val view = DataBindingUtil.inflate<ListItemBinding>(
                inflater,
                R.layout.list_item, parent, false
            )
            return ViewHolder(view)
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).binding.randevu = appointmentList[position]
    }

    override fun getItemCount(): Int {
        return appointmentList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(appointmentList: ArrayList<RandevuEntity>) {
        this.appointmentList.clear()
        this.appointmentList.addAll(appointmentList)
        notifyDataSetChanged()
    }

}
