package com.example.hastanerandevusistemi


import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.hastanerandevusistemi.register.RegisterDao
import com.example.hastanerandevusistemi.register.RegisterEntity

@Database(entities = [RegisterEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun  registerDao(): RegisterDao

}