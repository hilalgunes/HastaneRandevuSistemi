package com.example.hastanerandevusistemi.json.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "doktor")
data class DoktorEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @SerializedName("poliklinikId")
    val poliklinikId: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("text")
    val text: String? = null,
    @SerializedName("value")
    val value: Int? = null
)