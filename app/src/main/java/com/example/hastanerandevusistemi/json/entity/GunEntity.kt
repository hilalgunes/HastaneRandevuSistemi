package com.example.hastanerandevusistemi.json.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "gun")
data class GunEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @SerializedName("doktorId")
    val doktorId: Int,
    @SerializedName("text")
    val text: String,
    @SerializedName("value")
    val value: Int
)