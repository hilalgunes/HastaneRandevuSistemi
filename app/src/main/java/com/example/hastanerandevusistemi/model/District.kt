package com.example.hastanerandevusistemi.model

data class District(
    val hastane: List<Hastane>,
    val ilId: Int,
    val text: String,
    val value: Int
)