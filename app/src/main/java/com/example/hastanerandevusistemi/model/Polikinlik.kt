package com.example.hastanerandevusistemi.model

import com.example.hastanerandevusistemi.json.entity.PoliklinikEntity

data class Polikinlik(
    val doktor: List<Doktor>,
    val hastaneId: Int,
    val text: String,
    val value: Int
): java.io.Serializable{
    fun toPoliklinikEntity(): PoliklinikEntity{
        return PoliklinikEntity(
            hastaneId = this.hastaneId,
            text = this.text,
            value = this.value
        )
    }
}