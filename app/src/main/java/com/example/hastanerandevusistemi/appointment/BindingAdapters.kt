package com.example.hastanerandevusistemi.appointment

import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData

object BindingAdapters {
    @JvmStatic
    @BindingAdapter("app:entries")
    fun setSpinnerEntries(spinner: Spinner, data: MutableLiveData<String?>) {
        val context = spinner.context
        val entries = data.value?.let { listOf(it) } ?: emptyList()
        spinner.adapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, entries)
    }
}
