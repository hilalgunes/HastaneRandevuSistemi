package com.example.hastanerandevusistemi.json.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "hastane")
data class HastaneEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @SerializedName("ilceId")
    val ilceId: Int? = null,
    @SerializedName("text")
    val text: String? = null,
    @SerializedName("value")
    val value: Int? = null
)