package com.example.hastanerandevusistemi.model

data class Gunler(
    val doktorId: Int,
    val saatler: List<Saatler>,
    val text: String,
    val value: Int
)