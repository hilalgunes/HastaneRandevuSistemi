package com.example.hastanerandevusistemi.model

data class Doktor(
    val gunler: List<Gunler>,
    val name: String,
    val polikinlikId: Int,
    val surname: String,
    val text: String,
    val value: Int
)