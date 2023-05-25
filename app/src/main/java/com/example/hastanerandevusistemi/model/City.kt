package com.example.hastanerandevusistemi.model

import com.example.hastanerandevusistemi.json.entity.CityEntity

data class City(
    val districts: List<District>,
    val text: String,
    val value: Int
): java.io.Serializable{
    fun toCityEntity(): CityEntity {
        return CityEntity(
            value = this.value,
            name = this.text
        )
    }
}