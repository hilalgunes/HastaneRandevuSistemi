package com.example.hastanerandevusistemi.model

import com.example.hastanerandevusistemi.json.entity.SaatEntity
import com.google.gson.annotations.SerializedName


data class Saatler(
    @SerializedName("selected")
    val selected: Boolean,
    @SerializedName("gunId")
    val gunId: Int,
    @SerializedName("text")
    val text: String,
    @SerializedName("value")
    val value: Int
): java.io.Serializable {
    fun toSaatEntity(): SaatEntity {
        return SaatEntity(
            gunId = gunId,
            selected = selected,
            text = text,
            value = value
        )
    }
}
