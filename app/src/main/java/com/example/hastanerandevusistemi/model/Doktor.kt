package com.example.hastanerandevusistemi.model

import com.example.hastanerandevusistemi.json.entity.DoktorEntity
import com.google.gson.annotations.SerializedName

data class Doktor(
    @SerializedName("günler")
    val gunler: List<Gunler>,
    val poliklinikId: Int,
    val name: String,
    val text: String,
    val value: Int
): java.io.Serializable{
    fun toDoktorEntity(): DoktorEntity{
        return DoktorEntity(
            name = this.name,
            poliklinikId = this.poliklinikId,
            text = this.text,
            value = this.value
        )
    }
}