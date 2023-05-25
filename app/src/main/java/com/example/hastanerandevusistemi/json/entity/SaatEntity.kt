package com.example.hastanerandevusistemi.json.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "saat")
data class SaatEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @SerializedName("gun_id")
    val gunId: Int? = null,
    @SerializedName("selected")
    val selected: Boolean? = false,
    @SerializedName("text")
    val text: String? = null,
    @SerializedName("value")
    val value: Int? = null
)