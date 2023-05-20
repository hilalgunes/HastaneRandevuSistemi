package com.example.hastanerandevusistemi.appointment

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "randevu_table")
data class RandevuEntity (

    @PrimaryKey(autoGenerate = true)
    var userId: Int = 0,

    @ColumnInfo(name = "il")
    var il: String,

    @ColumnInfo(name = "ilce")
    var ilce: String,

    @ColumnInfo(name = "hastane")
    var hastane: String,

    @ColumnInfo(name = "poliklinik")
    var poliklinik: String,

    @ColumnInfo(name = "doktor")
    var doktor: String,

    @ColumnInfo(name = "gun")
    var gun: String,

    @ColumnInfo(name = "saat")
    var saat: String,

    )