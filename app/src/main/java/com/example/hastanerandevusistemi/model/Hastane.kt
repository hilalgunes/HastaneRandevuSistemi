package com.example.hastanerandevusistemi.model

import com.example.hastanerandevusistemi.json.entity.HastaneEntity

data class Hastane(
    val ilceId: Int,
    val polikinlik: List<Polikinlik>,
    val text: String,
    val value: Int
): java.io.Serializable{
    fun toHastaneEntity(): HastaneEntity {
        return HastaneEntity(
            ilceId = this.ilceId,
            text = this.text,
            value = this.value
        )
    }
}