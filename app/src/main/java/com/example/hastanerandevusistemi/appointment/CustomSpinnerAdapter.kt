package com.example.hastanerandevusistemi.appointment

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.hastanerandevusistemi.R
import com.example.hastanerandevusistemi.databinding.SpinnerItemBinding
import com.example.hastanerandevusistemi.json.entity.*

class CustomSpinnerAdapter(context: Context, private val items: List<Any>) :
    ArrayAdapter<Any>(context, 0, items) {
    private lateinit var itemBinding: SpinnerItemBinding
    val layoutInflater: LayoutInflater = LayoutInflater.from(context)
    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val item = items[position]
        itemBinding = SpinnerItemBinding.inflate(layoutInflater)
        when (item) {
            is CityEntity -> {
                itemBinding.spinnerItemText.text = item.name
            }
            is DistrictEntity -> {
                itemBinding.spinnerItemText.text = item.text
            }
            is HastaneEntity -> {
                itemBinding.spinnerItemText.text = item.text
            }
            is PoliklinikEntity -> {
                itemBinding.spinnerItemText.text = item.text
            }
            is DoktorEntity -> {
                itemBinding.spinnerItemText.text = item.text
            }
            is GunEntity -> {
                itemBinding.spinnerItemText.text = item.text
            }
            is SaatEntity -> {
                itemBinding.spinnerItemText.text = item.text
                if (item.selected == true) {
                    itemBinding.spinnerItemText.setTextColor(context.getColor(R.color.grey))
                }
            }
        }
        return itemBinding.root
    }
    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getView(position, convertView, parent)
    }
}