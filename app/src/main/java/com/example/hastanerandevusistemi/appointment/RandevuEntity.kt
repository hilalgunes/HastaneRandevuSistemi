package com.example.hastanerandevusistemi.appointment

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "randevu")
data class RandevuEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val userId: Int? = null,
    val cityId: Int? = null,
    val cityName: String? = null,
    val hospitalId: Int? = null,
    val hospitalName: String? = null,
    val departmentId: Int? = null,
    val departmentName: String? = null,
    val doctorId: Int? = null,
    val doctorName: String? = null,
    val dayId: Int? = null,
    val dayName: String? = null,
    val hourId: Int? = null,
    val hourName: String? = null
)