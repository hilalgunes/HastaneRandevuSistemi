package com.example.hastanerandevusistemi.json.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity("city")
data class CityEntity (
        @PrimaryKey(autoGenerate = true)
        var id: Int? = null,
        @SerializedName("value")
        val value: Int? = null,
        @SerializedName("text")
        val name: String? = null
        )
