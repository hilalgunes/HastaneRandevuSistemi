package com.example.hastanerandevusistemi.model

import com.example.hastanerandevusistemi.json.entity.GunEntity
import com.google.gson.annotations.SerializedName

data class Gunler(
    @SerializedName("doktorId")
    val doktorId: Int,
    @SerializedName("saatler")
    val saatler: List<Saatler>,
    @SerializedName("text")
    val text: String,
    @SerializedName("value")
    val value: Int
): java.io.Serializable{
    fun toGunEntity(): GunEntity {
        return GunEntity(
            doktorId = this.doktorId,
            text = this.text,
            value = this.value
        )
    }
}