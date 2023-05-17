package com.example.hastanerandevusistemi.register

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Register_users_table")
data class RegisterEntity (

    @PrimaryKey(autoGenerate = true)
    var userId: Int = 0,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "surname")
    var surname: String,

    @ColumnInfo(name = "tcNo")
    var TC: String,

    @ColumnInfo(name = "gender")
    var gender: String,

    @ColumnInfo(name = "birthday")
    var birthday: String,

    @ColumnInfo(name = "email")
    var email: String,

    @ColumnInfo(name = "phone")
    var phone: String,

    @ColumnInfo(name = "password")
    var password: String,


)