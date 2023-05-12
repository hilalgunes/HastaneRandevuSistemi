package com.example.hastanerandevusistemi.model

data class Polikinlik(
    val doktor: List<Doktor>,
    val hastaneId: Int,
    val text: String,
    val value: Int
)