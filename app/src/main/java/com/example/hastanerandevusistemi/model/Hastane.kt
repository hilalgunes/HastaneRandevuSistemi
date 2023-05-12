package com.example.hastanerandevusistemi.model

data class Hastane(
    val ilceId: Int,
    val polikinlik: List<Polikinlik>,
    val text: String,
    val value: Int
)